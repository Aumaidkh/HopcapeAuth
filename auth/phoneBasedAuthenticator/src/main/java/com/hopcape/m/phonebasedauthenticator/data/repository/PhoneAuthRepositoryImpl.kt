package com.hopcape.m.phonebasedauthenticator.data.repository

import com.hopcape.m.common.AuthResult
import com.hopcape.m.common.datatypes.Phone
import com.hopcape.m.phonebasedauthenticator.data.api.FirebasePhoneVerificationApi
import com.hopcape.m.phonebasedauthenticator.domain.Errors
import com.hopcape.m.phonebasedauthenticator.domain.model.OTP
import javax.inject.Inject

internal class PhoneAuthRepositoryImpl @Inject constructor(
    private val api: FirebasePhoneVerificationApi
) : PhoneAuthRepository {

    override suspend fun requestOtp(number: Phone): AuthResult<Unit, Errors> {
        return api.requestOtp(number.value)
    }

    override suspend fun verifyOtp(
        number: Phone,
        otp: OTP
    ): AuthResult<Unit, Errors> {
        return api.verifyOtp(otp.value)
    }
}