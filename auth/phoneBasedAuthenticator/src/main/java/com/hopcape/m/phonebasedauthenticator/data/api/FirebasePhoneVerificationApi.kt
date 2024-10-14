package com.hopcape.m.phonebasedauthenticator.data.api

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.hopcape.m.common.AuthResult
import com.hopcape.m.common.Error
import com.hopcape.m.phonebasedauthenticator.domain.Errors
import dagger.Lazy
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.coroutines.resume

class FirebasePhoneVerificationApi @Inject constructor(
    private val firebaseAuth: Lazy<FirebaseAuth>
) {
    private var config: PhoneVerificationConfig? = null
    private var verificationID: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    private val phoneAuthOptionBuilder by lazy {
        PhoneAuthOptions.newBuilder(firebaseAuth.get())
    }

    fun configure(config: PhoneVerificationConfig) {
        this.config = config
    }

    suspend fun requestOtp(phone: String): AuthResult<Unit, Errors> {
        return suspendCancellableCoroutine { continuation ->
            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(credential: PhoneAuthCredential) = Unit

                override fun onVerificationFailed(exception: FirebaseException) = Unit

                override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    continuation.resume(AuthResult.Success(Unit))
                    // Save verificationId and token for later use in verification
                    verificationID = verificationId
                    resendToken = token
                }
            }

            config?.let {
                val options = phoneAuthOptionBuilder
                    .setPhoneNumber(phone)
                    .setActivity(it.activity)
                    .setTimeout(
                        60,
                        TimeUnit.SECONDS
                    )
                    .setCallbacks(callbacks)
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
            } ?: throw IllegalStateException("No Activity attached to PhoneAuthProvider")

        }
    }

    suspend fun verifyOtp(otp: String): AuthResult<Unit, Errors> {
        val verificationId = verificationID ?: return AuthResult.Error(Errors.INTERNAL_SERVER_ERROR)
        val credential = PhoneAuthProvider.getCredential(
            verificationId,
            otp
        )
        return suspendCancellableCoroutine { continuation ->
            firebaseAuth.get().signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(AuthResult.Success(Unit))
                    } else {
                        task.exception
                        continuation.resume(AuthResult.Error(Errors.INTERNAL_SERVER_ERROR))
                    }
                }
        }
    }
}