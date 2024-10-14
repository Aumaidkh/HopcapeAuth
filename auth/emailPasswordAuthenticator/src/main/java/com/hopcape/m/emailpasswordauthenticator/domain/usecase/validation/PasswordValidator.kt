package com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation

import com.hopcape.m.common.datatypes.Password
import com.hopcape.m.common.wrappers.UseCaseResult


class PasswordValidator (
    private val regex: Regex = Regex(REGEX),
    private val passwordLength: Int = 8
): UseCaseResult<Unit, ValidationError>() {

    operator fun invoke(input: Password): UseCaseResult<Unit,ValidationError> {
        if (input.value.isEmpty()){
            return Error(ValidationError.EMPTY_PASSWORD)
        }
        if (input.value.length < passwordLength){
            return Error(ValidationError.PASSWORD_TOO_SHORT)
        }
//        if (!input.value.matches(regex)){
//            return Error(ValidationError.INVALID_PASSWORD)
//        }
        return Success(Unit)
    }

    companion object {
        private const val REGEX = "^(?=.*[0-9])(?=.*[!@#\$%^&*()_+|~=`{}\\[\\]:\";'<>?,./-])(?=.*[A-Z])(?=.*[a-z]).{8,}\$"
    }
}