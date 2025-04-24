package com.frommetoyou.bistrosoft.di

import com.frommetoyou.common.CoroutinesDispatcherProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun provideCoroutineDispatchers(): CoroutinesDispatcherProvider {
        return CoroutinesDispatcherProvider()
    }

}