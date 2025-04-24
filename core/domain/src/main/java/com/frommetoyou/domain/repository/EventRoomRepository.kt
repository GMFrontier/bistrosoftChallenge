package com.frommetoyou.domain.repository

import com.frommetoyou.domain.model.Event
import kotlinx.coroutines.flow.Flow

interface EventRoomRepository {

    suspend fun getEvents(): Flow<Result<List<Event>>>
    suspend fun saveEvent(event: Event): Flow<Result<Long>>
    suspend fun clearEvents(): Flow<Result<Int>>
}