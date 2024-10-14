package com.hopcape.m.sessionmanager.data.di

import android.content.Context
import com.hopcape.m.encryptionmanager.manager.EncryptionManager
import com.hopcape.m.jsonparser.JsonParser
import com.hopcape.m.sessionmanager.data.datasource.SharedPrefsSessionManager
import com.hopcape.m.sessionmanager.data.repository.SessionRepository
import com.hopcape.m.sessionmanager.data.repository.SessionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModuleBindings {

    @Binds
    internal abstract fun bindsSessionRepository(impl: SessionRepositoryImpl): SessionRepository


    companion object {

        @Provides
        @Singleton
        fun provideSharedPreferencesManager(
            @ApplicationContext context: Context,
            encryptionManager: EncryptionManager,
            jsonParser: JsonParser
        ): SharedPrefsSessionManager {
            return SharedPrefsSessionManager(
                context = context,
                encryptionManager = encryptionManager,
                jsonParser = jsonParser
            )
        }
    }
}