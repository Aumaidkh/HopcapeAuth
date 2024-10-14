package com.hopcape.m.authcontroller.di

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.hopcape.m.authcontroller.domain.AuthController
import com.hopcape.m.authcontroller.data.AuthControllerImpl
import dagger.Binds
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthControllerBindings {

    @Binds
    internal abstract fun bindAuthController(impl: AuthControllerImpl): AuthController

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseAuth(
            @ApplicationContext context: Context
        ): FirebaseAuth {
            //FirebaseApp.initializeApp(context)
            return FirebaseAuth.getInstance()
        }

        @Provides
        fun provideCoroutineScope(): CoroutineScope {
            return CoroutineScope(Dispatchers.IO)
        }
    }
}