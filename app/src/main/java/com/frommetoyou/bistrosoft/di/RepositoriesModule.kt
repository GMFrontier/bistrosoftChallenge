package com.frommetoyou.bistrosoft.di

import com.frommetoyou.common.CoroutinesDispatcherProvider
import com.frommetoyou.data.repository.DogsRepositoryImpl
import com.frommetoyou.data.repository.EventRoomRepositoryImpl
import com.frommetoyou.data.service.DogsApiService
import com.frommetoyou.data.local.EventDao
import com.frommetoyou.domain.repository.DogsRepository
import com.frommetoyou.domain.repository.EventRoomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Singleton
    @Provides
    fun provideEventRoomRepository(
        eventDao: com.frommetoyou.data.local.EventDao,
        coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    ): EventRoomRepository {
        return EventRoomRepositoryImpl(eventDao, coroutinesDispatcherProvider)
    }

    @Singleton
    @Provides
    fun provideDogsRepository(
        dogsApiService: DogsApiService,
        coroutinesDispatcherProvider: CoroutinesDispatcherProvider
    ): DogsRepository {
        return DogsRepositoryImpl(dogsApiService, coroutinesDispatcherProvider)
    }
}