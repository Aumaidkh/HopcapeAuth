package com.hopcape.m.emailpasswordauthenticator.domain.usecase

import com.hopcape.m.common.Error
import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.datatypes.Password
import com.hopcape.m.common.models.UserModel
import com.hopcape.m.common.wrappers.UseCaseResult
import com.hopcape.m.emailpasswordauthenticator.data.repository.EmailPasswordAuthenticationRepository
import com.hopcape.m.common.error.DomainError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SignInUser @Inject constructor(
    private val repository: EmailPasswordAuthenticationRepository
){

    operator fun invoke(email: Email,password: Password): Flow<UseCaseResult<UserModel,out Error>> {
        return flow {
            emit(UseCaseResult.Loading())
            val authResult =
                repository.signIn(email, password)
            val useCaseResult =
                UseCaseResult.fromAuthResult(authResult)
            emit(useCaseResult)
        }.catch {
            emit(UseCaseResult.Error(DomainError.NO_INTERNET))
        }
    }

}