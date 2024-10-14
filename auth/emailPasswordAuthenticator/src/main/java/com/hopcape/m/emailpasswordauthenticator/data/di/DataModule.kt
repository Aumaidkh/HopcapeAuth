package com.hopcape.m.emailpasswordauthenticator.data.di

import com.google.firebase.auth.FirebaseAuth
import com.hopcape.m.emailpasswordauthenticator.data.datasource.FirebaseDatasource
import com.hopcape.m.emailpasswordauthenticator.data.repository.EmailPasswordAuthenticationRepositoryImpl
import com.hopcape.m.emailpasswordauthenticator.data.repository.EmailPasswordAuthenticationRepository
import com.hopcape.tracemanager.TraceManager
import dagger.Binds
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    internal abstract fun bindsEmailPasswordAuthenticationRepository(impl: EmailPasswordAuthenticationRepositoryImpl): EmailPasswordAuthenticationRepository

    companion object {

        @Provides
        @Singleton
        internal fun providesFirebaseDatasource(
            firebaseAuth: Lazy<FirebaseAuth>,
            traceManager: Lazy<TraceManager>
        ): FirebaseDatasource {
            return FirebaseDatasource(firebaseAuth,traceManager)
        }
    }
}