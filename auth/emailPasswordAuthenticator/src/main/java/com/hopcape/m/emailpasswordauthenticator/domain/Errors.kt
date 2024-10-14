package com.hopcape.m.emailpasswordauthenticator.domain

import com.hopcape.m.common.Error

enum class Errors(val message: String) : Error {
    NO_INTERNET("No internet connection detected"),
    INVALID_EMAIL_PASSWORD("Invalid email or password"),
    SOMETHING_WENT_WRONG("Something went wrong..")
}