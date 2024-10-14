package com.hopcape.m.phonebasedauthenticator.domain.usecase

import com.hopcape.m.common.datatypes.Phone
import com.hopcape.m.common.wrappers.UseCaseResult
import com.hopcape.m.phonebasedauthenticator.data.repository.PhoneAuthRepository
import com.hopcape.m.phonebasedauthenticator.domain.Errors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class RequestOtp @Inject constructor(
    private val repository: PhoneAuthRepository
){
    fun invoke(phone: Phone): Flow<UseCaseResult<out Unit,out Errors>>{
        return flow {
            emit(UseCaseResult.Loading())
            val requestResult =
                repository.requestOtp(phone)
            val useCaseResult =
                UseCaseResult.fromAuthResult(requestResult)
            emit(useCaseResult)
        }.catch {
            emit(UseCaseResult.Error(Errors.UNKNOWN_ERROR))
        }
    }
}