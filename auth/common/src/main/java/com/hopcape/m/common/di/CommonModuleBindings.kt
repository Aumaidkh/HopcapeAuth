package com.hopcape.m.common.di

import com.hopcape.m.common.error.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CommonModuleBindings {

    companion object {
        @Provides
        fun provideErrorHandler(): ErrorHandler {
            return ErrorHandler()
        }
    }
}