package com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation

import com.hopcape.m.common.Error

enum class ValidationError: Error {
    EMTPY_EMAIL,
    INVALID_EMAIL,
    BLANK_EMAIL,
    EMPTY_PASSWORD,
    PASSWORD_TOO_SHORT,
    INVALID_PASSWORD
}