package com.hopcape.m.common.error

import com.hopcape.m.common.Error
import com.hopcape.m.common.error.DataError.*
import com.hopcape.m.common.error.DomainError.*

class ErrorHandler {
    fun resolveError(error: Error): String {
        return when(error){
            is DataError -> error.getMessage()
            is DomainError -> error.getMessage()
            else -> "Unknown Error"
        }
    }
    private fun DataError.getMessage(): String {
        return when(this){
            INVALID_EMAIL_AND_PASSWORD -> "Invalid email or password"
            EMAIL_NOT_VERIFIED -> "Email not verified"
            UNKNOWN_ERROR -> "Unknown Error"
            CONNECTION_ERROR -> "Connection Error"
            USER_NOT_FOUND -> "User not found"
            ERROR_EMAIL_ALREADY_IN_USE -> "Email already exists"
            ERROR_WEAK_PASSWORD -> "Password too weak"
        }
    }
    private fun DomainError.getMessage(): String {
        return when(this){
            EMTPY_EMAIL -> "Email can't be empty"
            NO_INTERNET -> "No internet connection detected"
            INVALID_EMAIL_PASSWORD -> "Invalid email or password"
            SOMETHING_WENT_WRONG -> "Something went wrong"
            INVALID_EMAIL -> "Invalid email"
            BLANK_EMAIL -> "Email can't be blank"
            EMPTY_PASSWORD -> "Password can't be empty"
            PASSWORD_TOO_SHORT -> "Password too short"
            INVALID_PASSWORD -> "Invalid password"
        }
    }
}