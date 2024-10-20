package com.hopcape.m.emailpasswordauthenticator.domain.usecase

import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.datatypes.FullName
import com.hopcape.m.common.datatypes.Password
import com.hopcape.m.common.wrappers.UseCaseResult
import com.hopcape.m.emailpasswordauthenticator.data.repository.EmailPasswordAuthenticationRepository
import com.hopcape.m.emailpasswordauthenticator.domain.Errors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegisterUser @Inject constructor (
    private val repository: EmailPasswordAuthenticationRepository
) {

    operator fun invoke(
        email: Email,
        password: Password,
        fullName: FullName?
    ): Flow<UseCaseResult<Unit,Errors>>{
        return flow {
            val registrationResult =
                repository.register(email,password,fullName)
            val useCaseResult =
                UseCaseResult.fromAuthResult(registrationResult)
            emit(useCaseResult)
        }.catch {
            emit(UseCaseResult.Error(Errors.NO_INTERNET))
        }
    }
}