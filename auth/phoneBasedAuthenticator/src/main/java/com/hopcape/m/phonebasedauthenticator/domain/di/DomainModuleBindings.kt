package com.hopcape.m.phonebasedauthenticator.domain.di

import com.hopcape.m.phonebasedauthenticator.data.repository.PhoneAuthRepository
import com.hopcape.m.phonebasedauthenticator.domain.usecase.RequestOtp
import com.hopcape.m.phonebasedauthenticator.domain.usecase.VerifyOtp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DomainModuleBindings {

    companion object {
        @Provides
        internal fun provideRequestOtp(
            repository: PhoneAuthRepository
        ): RequestOtp {
            return RequestOtp(repository)
        }
        @Provides
        internal fun provideVerifyOtp(
            repository: PhoneAuthRepository
        ): VerifyOtp {
            return VerifyOtp(repository)
        }
    }
}