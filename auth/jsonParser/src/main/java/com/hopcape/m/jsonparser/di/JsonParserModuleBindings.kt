package com.hopcape.m.jsonparser.di

import com.google.gson.Gson
import com.hopcape.m.jsonparser.GsonParser
import com.hopcape.m.jsonparser.JsonParser
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class JsonParserModuleBindings {

    @Binds
    internal abstract fun bindsJsonParser(impl: GsonParser): JsonParser

    companion object {
        @Provides
        internal fun providesGson(): Gson {
            return Gson()
        }
    }

}