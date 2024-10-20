package com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation

import com.hopcape.m.common.datatypes.Email
import com.hopcape.m.common.error.DomainError
import com.hopcape.m.common.wrappers.UseCaseResult


class EmailValidator (
    private val regex: Regex = Regex(REGEX)
) : UseCaseResult<Unit, DomainError>() {

    operator fun invoke(email: Email): UseCaseResult<Unit, DomainError> {
        if (email.value.isEmpty()) {
            return Error(DomainError.EMTPY_EMAIL)
        }

        if (email.value.isBlank()) {
            return Error(DomainError.BLANK_EMAIL)
        }

        if (!email.value.matches(regex)) {
            return Error(DomainError.INVALID_EMAIL)
        }

        return Success(Unit)
    }

    companion object {
        const val REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$"
    }
}