package com.hopcape.tracemanager

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TraceManagerModule {

    @Provides
    internal fun provideTraceManager(): TraceManager {
        return AndroidLogger()
    }
}