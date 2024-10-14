package com.hopcape.m.common

typealias RootError = Error

sealed interface AuthResult<D,E: RootError> {
    data class Success<D,E:RootError>(val data: D):AuthResult<D,E>
    data class Error<D,E: RootError>(val error: E): AuthResult<D,E>
}

inline fun <D,E:RootError,F: RootError> AuthResult<D,E>.mapError(transform: E.() -> F): AuthResult<D,F> =
    when(this){
        is AuthResult.Error -> AuthResult.Error(error.transform())
        is AuthResult.Success -> AuthResult.Success(data)
    }

interface Error