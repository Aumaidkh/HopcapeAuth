package com.hopcape.m.emailpasswordauthenticator.data.repository

import com.hopcape.m.common.AuthResult
import com.hopcape.m.common.Error
import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.datatypes.FullName
import com.hopcape.m.common.datatypes.Password
import com.hopcape.m.common.mapError
import com.hopcape.m.common.models.UserModel
import com.hopcape.m.emailpasswordauthenticator.data.datasource.FirebaseDatasource
import com.hopcape.m.emailpasswordauthenticator.domain.Errors
import javax.inject.Inject

internal class EmailPasswordAuthenticationRepositoryImpl @Inject constructor(
    private val datasource: FirebaseDatasource
) : EmailPasswordAuthenticationRepository {

    override suspend fun signIn(
        email: Email,
        password: Password
    ): AuthResult<UserModel,out Error> {
        return datasource.signIn(
            email = email,
            password = password
        )
    }

    override suspend fun register(
        email: Email,
        password: Password,
        fullname: FullName?
    ): AuthResult<Unit, Errors> {
        return datasource.register(
            email = email,
            password = password,
            fullName = fullname
        ).mapError {
            Errors.INVALID_EMAIL_PASSWORD
        }
    }

    override suspend fun sendResetPasswordLink(email: Email): AuthResult<Unit,out Error> {
        return datasource.sendResetPasswordLink(
            email = email
        )
    }

    override suspend fun checkUserExists(email: Email): AuthResult<Unit, out Error> {
        return datasource.userCheck(email)
    }
}