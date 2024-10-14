package com.hopcape.m.phonebasedauthenticator.data.di

import com.google.firebase.auth.FirebaseAuth
import com.hopcape.m.phonebasedauthenticator.data.api.FirebasePhoneVerificationApi
import com.hopcape.m.phonebasedauthenticator.data.repository.PhoneAuthRepository
import com.hopcape.m.phonebasedauthenticator.data.repository.PhoneAuthRepositoryImpl
import dagger.Binds
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModuleBindings {

    @Binds
    internal abstract fun bindsPhoneAuthRepository(impl: PhoneAuthRepositoryImpl): PhoneAuthRepository

    companion object {
        @Provides
        internal fun provideFirebasePhoneVerificationApi(firebaseAuth: Lazy<FirebaseAuth>) =
            FirebasePhoneVerificationApi(firebaseAuth)
    }
}