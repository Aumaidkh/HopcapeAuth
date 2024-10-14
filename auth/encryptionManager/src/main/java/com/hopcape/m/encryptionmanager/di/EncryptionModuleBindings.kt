package com.hopcape.m.encryptionmanager.di

import com.hopcape.m.encryptionmanager.decryptor.AndroidDecryptor
import com.hopcape.m.encryptionmanager.decryptor.Decryptor
import com.hopcape.m.encryptionmanager.encryptor.AndroidEncryptor
import com.hopcape.m.encryptionmanager.encryptor.Encryptor
import com.hopcape.m.encryptionmanager.manager.EncryptionManager
import com.hopcape.m.encryptionmanager.manager.EncryptionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class EncryptionModuleBindings {

    @Binds
    internal abstract fun bindsEncryptionManager(impl: EncryptionManagerImpl): EncryptionManager

    companion object {
        @Provides
        @Singleton
        internal fun providesEncryptor(): Encryptor {
            return AndroidEncryptor()
        }
        @Provides
        @Singleton
        internal fun providesDecryptor(): Decryptor {
            return AndroidDecryptor()
        }
    }
}