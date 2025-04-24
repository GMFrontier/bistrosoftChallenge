package com.frommetoyou.domain.use_case

import com.frommetoyou.domain.model.Event
import com.frommetoyou.domain.repository.EventRoomRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EventsUseCase @Inject constructor(
    private val eventsRoomRepository: EventRoomRepository
) {

    suspend fun getEvents(): Flow<Result<List<Event>>> = eventsRoomRepository.getEvents()
    suspend fun addEvent(screenName: String, eventName: String): Flow<Result<Long>>  {
        val event = Event(
            name = eventName,
            screenName = screenName,
            date = System.currentTimeMillis().toString()
        )

        return eventsRoomRepository.saveEvent(event)
    }
    suspend fun clearEvents(): Flow<Result<Int>> = eventsRoomRepository.clearEvents()

}