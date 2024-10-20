package com.hopcape.m.common.error

import com.hopcape.m.common.Error

enum class DomainError: Error {
    NO_INTERNET,
    INVALID_EMAIL_PASSWORD,
    SOMETHING_WENT_WRONG,
    EMTPY_EMAIL,
    INVALID_EMAIL,
    BLANK_EMAIL,
    EMPTY_PASSWORD,
    PASSWORD_TOO_SHORT,
    INVALID_PASSWORD,
    NAME_EMPTY,
    NAME_BLANK,
    NAME_TOO_SHORT,
    INVALID_NAME
}