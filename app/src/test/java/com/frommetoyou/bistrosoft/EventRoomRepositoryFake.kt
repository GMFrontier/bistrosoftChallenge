package com.frommetoyou.bistrosoft

import com.frommetoyou.data.parseData
import com.frommetoyou.domain.model.Event
import com.frommetoyou.domain.repository.EventRoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class EventRoomRepositoryFake : EventRoomRepository {

    var events = mutableListOf<Event>()

    override suspend fun getEvents(): Flow<Result<List<Event>>> = flow {
        emit(events.parseData())
    }

    override suspend fun saveEvent(event: Event): Flow<Result<Long>> = flow {
        events.add(event)
        emit(Math.random().toLong().parseData())

    }

    override suspend fun clearEvents(): Flow<Result<Int>> = flow {
        events.clear()
        emit(Math.random().toInt().parseData())
    }
}