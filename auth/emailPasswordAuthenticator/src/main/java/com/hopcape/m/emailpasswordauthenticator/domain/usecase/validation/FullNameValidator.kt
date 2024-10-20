package com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation

import com.hopcape.m.common.datatypes.FullName
import com.hopcape.m.common.error.DomainError
import com.hopcape.m.common.wrappers.UseCaseResult

class FullNameValidator: UseCaseResult<Unit,DomainError>() {
    operator fun invoke(fullName: FullName): UseCaseResult<Unit,DomainError>{
        if (fullName.isEmpty){
            return Error(DomainError.NAME_EMPTY)
        }
        if (fullName.isBlank){
            return Error(DomainError.NAME_BLANK)
        }
        if (fullName.length < 2){
            return Error(DomainError.NAME_TOO_SHORT)
        }
        if (fullName.isValid){
            return Error(DomainError.INVALID_NAME)
        }

        return Success(Unit)
    }
}