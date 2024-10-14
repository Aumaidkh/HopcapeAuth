package com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation

import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.wrappers.UseCaseResult


class EmailValidator (
    private val regex: Regex = Regex(REGEX)
) : UseCaseResult<Unit, ValidationError>() {

    operator fun invoke(email: Email): UseCaseResult<Unit, ValidationError> {
        if (email.value.isEmpty()) {
            return Error(ValidationError.EMTPY_EMAIL)
        }

        if (email.value.isBlank()) {
            return Error(ValidationError.BLANK_EMAIL)
        }

        if (!email.value.matches(regex)) {
            return Error(ValidationError.INVALID_EMAIL)
        }

        return Success(Unit)
    }

    companion object {
        const val REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
    }
}