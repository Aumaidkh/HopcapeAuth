package com.hopcape.m.emailpasswordauthenticator.data.repository

import com.hopcape.m.common.AuthResult
import com.hopcape.m.common.Error
import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.datatypes.FullName
import com.hopcape.m.common.datatypes.Password
import com.hopcape.m.common.models.UserModel
import com.hopcape.m.common.error.DomainError

interface EmailPasswordAuthenticationRepository {

    suspend fun checkUserExists(
        email: Email
    ): AuthResult<Unit,out Error>

    suspend fun signIn(
        email: Email,
        password: Password
    ): AuthResult<UserModel,out Error>

    suspend fun register(
        email: Email,
        password: Password,
        fullname: FullName? = null
    ): AuthResult<Unit, DomainError>

    suspend fun sendResetPasswordLink(
        email: Email,
    ): AuthResult<Unit,out Error>
}