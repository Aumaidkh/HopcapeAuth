package com.hopcape.m.emailpasswordauthenticator.domain.usecase
// 4th feb
import com.hopcape.m.common.Error
import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.models.UserModel
import com.hopcape.m.common.wrappers.UseCaseResult
import com.hopcape.m.emailpasswordauthenticator.data.repository.EmailPasswordAuthenticationRepository
import com.hopcape.m.emailpasswordauthenticator.domain.Errors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class FindAccount @Inject constructor(
    private val repository: EmailPasswordAuthenticationRepository
) {
    operator fun invoke(email: Email): Flow<UseCaseResult<Unit,out Error>> {
        return flow {
            emit(UseCaseResult.Loading())
            val authResult =
                repository.checkUserExists(email)
            val useCaseResult =
                UseCaseResult.fromAuthResult(authResult)
            emit(useCaseResult)
        }.catch {
            emit(UseCaseResult.Error(Errors.NO_INTERNET))
        }
    }
}