package com.hopcape.m.emailpasswordauthenticator.domain.di

import com.hopcape.m.emailpasswordauthenticator.data.repository.EmailPasswordAuthenticationRepository
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.RegisterUser
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.ResendPassword
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.SignInUser
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation.EmailValidator
import com.hopcape.m.emailpasswordauthenticator.domain.usecase.validation.PasswordValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DomainModule {

    companion object {
        @Provides
        @ViewModelScoped
        internal fun providesSignInUseCase(
            repository: EmailPasswordAuthenticationRepository
        ): SignInUser {
            return SignInUser(repository)
        }

        @Provides
        @ViewModelScoped
        internal fun providesRegisterUserUseCase(
            repository: EmailPasswordAuthenticationRepository
        ): RegisterUser {
            return RegisterUser(repository)
        }

        @Provides
        @ViewModelScoped
        internal fun providesResendPasswordLinkUserUseCase(
            repository: EmailPasswordAuthenticationRepository
        ): ResendPassword {
            return ResendPassword(repository)
        }

        @Provides
        @ViewModelScoped
        internal fun providesEmailValidatorUseCase(): EmailValidator {
            return EmailValidator()
        }

        @Provides
        @ViewModelScoped
        internal fun providesPasswordValidatorUserUseCase(): PasswordValidator {
            return PasswordValidator()
        }
    }
}