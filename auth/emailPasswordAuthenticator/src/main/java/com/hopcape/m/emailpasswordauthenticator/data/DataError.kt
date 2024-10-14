package com.hopcape.m.emailpasswordauthenticator.data

import com.hopcape.m.common.Error

enum class DataError : Error {
    EMAIL_NOT_VERIFIED,
    UNKNOWN_ERROR,
    CONNECTION_ERROR,
    USER_NOT_FOUND,
    INVALID_EMAIL_AND_PASSWORD,
    ERROR_EMAIL_ALREADY_IN_USE,
    ERROR_WEAK_PASSWORD
}