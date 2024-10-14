package com.hopcape.m.common.wrappers

import com.hopcape.m.common.AuthResult
import com.hopcape.m.common.RootError

abstract class UseCaseResult<D, E: RootError>(open val error: E?=null){
    class Loading<D,E:RootError> : UseCaseResult<D, E>()
    data class Success<D,E:RootError>(val data: D) : UseCaseResult<D, E>()
    data class Error<D,E: RootError>(override val error: E) : UseCaseResult<D, E>(error)

    companion object {
        fun <D,E: RootError> fromAuthResult(result: AuthResult<D,E>): UseCaseResult<D,E> {
            return when(result){
                is AuthResult.Success -> Success(result.data)
                is AuthResult.Error -> Error(result.error)
            }
        }
    }
}