package com.frommetoyou.data.repository

import com.frommetoyou.common.CoroutinesDispatcherProvider
import com.frommetoyou.data.parseData
import com.frommetoyou.data.local.EventDao
import com.frommetoyou.domain.model.Event
import com.frommetoyou.domain.repository.EventRoomRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EventRoomRepositoryImpl @Inject constructor(
    private val eventsDao: EventDao,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : EventRoomRepository {
    override suspend fun getEvents(): Flow<Result<List<Event>>> =
        eventsDao.getEvents()
            .map { result -> result.parseData() }
            .catch { error -> emit(Result.failure(error)) }
            .flowOn(coroutinesDispatcherProvider.io)

    override suspend fun saveEvent(event: Event): Flow<Result<Long>> = flow {
        emit(eventsDao.addEvent(event))
    }
        .map { result -> result.parseData() }
        .catch { error -> emit(Result.failure(error)) }
        .flowOn(coroutinesDispatcherProvider.io)

    override suspend fun clearEvents(): Flow<Result<Int>> = flow {
        emit(eventsDao.deleteEvents())
    }
        .map { result -> result.parseData() }
        .catch { error -> emit(Result.failure(error)) }
        .flowOn(coroutinesDispatcherProvider.io)
}