package com.hopcape.m.emailpasswordauthenticator.data.datasource

import com.google.firebase.auth.FirebaseAuth
import com.hopcape.m.common.AuthResult
import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.datatypes.FullName
import com.hopcape.m.common.datatypes.ID
import com.hopcape.m.common.datatypes.Password
import com.hopcape.m.common.datatypes.Phone
import com.hopcape.m.common.models.UserModel
import com.hopcape.m.emailpasswordauthenticator.data.DataError
import com.hopcape.tracemanager.TraceConfig
import com.hopcape.tracemanager.TraceManager
import kotlinx.coroutines.tasks.await
import java.net.URL
import javax.inject.Inject


internal class FirebaseDatasource @Inject constructor(
    private val firebaseAuth: dagger.Lazy<FirebaseAuth>,
    traceManager: dagger.Lazy<TraceManager>
) {

    private val logger by lazy {
        traceManager.get().also {
            it.configure(object : TraceConfig{
                override val tag: String
                    get() = FirebaseDatasource::class.java.name
            })
        }
    }

    suspend fun signIn(
        email: Email,
        password: Password
    ): AuthResult<UserModel, DataError> {
        return safeFirebaseCall(
            onFailure = { logger.logFailure(::signIn.name,it) },
            block = {
                val result = firebaseAuth.get().signInWithEmailAndPassword(
                    email.value,
                    password.value
                ).await()

                result?.user?.let {
                    if (!it.isEmailVerified) {
                        logger.logAction(::signIn.name,"Email Not Verified")
                        return AuthResult.Error(DataError.EMAIL_NOT_VERIFIED)
                    }

                    return AuthResult.Success(
                        data = UserModel(
                            id = ID(it.uid),
                            email = it.email?.let { email -> Email(email) },
                            phone = it.phoneNumber?.let { phone -> Phone(phone) },
                            displayPic = it.photoUrl?.let { url -> URL(url.toString()) }
                        )
                    )
                } ?: return AuthResult.Error<UserModel, DataError>(
                    error = DataError.USER_NOT_FOUND
                ).also {
                    logger.logFailure(::signIn.name,"User Not Found..")
                }
            }
        )
    }

    suspend fun register(
        email: Email,
        password: Password,
        fullName: FullName?
    ): AuthResult<Unit,DataError> {
        return safeFirebaseCall(
            onFailure = { logger.logFailure(::register.name,it) },
            block = {
                val result = firebaseAuth.get().createUserWithEmailAndPassword(
                    email.value,
                    password.value
                ).await()

                result?.user?.let {
                    it.sendEmailVerification().await()
                    return AuthResult.Success(Unit)
                } ?: return AuthResult.Error(
                    error = DataError.USER_NOT_FOUND
                )
            }
        )
    }

    suspend fun sendResetPasswordLink(email: Email): AuthResult<Unit, DataError> {
        return safeFirebaseCall(
            onFailure = { logger.logFailure(::register.name,it) },
            block = {
                firebaseAuth.get().sendPasswordResetEmail(
                    email.value
                ).await()
                return AuthResult.Success(Unit)
            }
        )
    }

    suspend fun userCheck(email: Email): AuthResult<Unit, DataError> {
        return safeFirebaseCall(
            onFailure = { logger.logFailure(::register.name,it) },
            block = {
                firebaseAuth.get().fetchSignInMethodsForEmail(
                    email.value
                ).await().signInMethods?.also {
                    if (it.isEmpty()){
                        return AuthResult.Error(DataError.USER_NOT_FOUND)
                    }
                    return AuthResult.Success(Unit)
                }
                return AuthResult.Error(DataError.USER_NOT_FOUND)
            }
        )
    }

}