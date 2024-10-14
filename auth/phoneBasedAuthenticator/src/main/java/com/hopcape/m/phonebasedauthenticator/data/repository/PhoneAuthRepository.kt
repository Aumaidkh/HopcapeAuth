package com.hopcape.m.phonebasedauthenticator.data.repository

import com.hopcape.m.common.AuthResult
import com.hopcape.m.common.datatypes.Phone
import com.hopcape.m.phonebasedauthenticator.domain.Errors
import com.hopcape.m.phonebasedauthenticator.domain.model.OTP

internal interface PhoneAuthRepository {

    suspend fun requestOtp(number: Phone): AuthResult<Unit,Errors>

    suspend fun verifyOtp(number: Phone,otp: OTP): AuthResult<Unit,Errors>
}