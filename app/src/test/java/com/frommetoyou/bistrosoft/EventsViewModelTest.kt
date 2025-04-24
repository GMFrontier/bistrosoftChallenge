package com.frommetoyou.bistrosoft

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.frommetoyou.domain.model.Event
import com.frommetoyou.domain.use_case.EventsUseCase
import com.frommetoyou.presentation.EventsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class EventsViewModelTest {

    private lateinit var viewModel: EventsViewModel
    private lateinit var eventsUseCase: EventsUseCase
    private lateinit var repository: EventRoomRepositoryFake

    @BeforeEach
    fun setUp() {
        repository = EventRoomRepositoryFake()
        eventsUseCase = EventsUseCase(repository)
        viewModel = EventsViewModel(
            eventsUseCase = eventsUseCase
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Testear que cada ingreso de evento se guarda y trae correctamente`() = runBlocking<Unit> {
        val event = Event(
            name = "evento1",
            screenName = "screen1",
            date = System.currentTimeMillis().toString()
        )

        viewModel.events.test {

            val emission1 = awaitItem()
            assertThat(emission1.size).isEqualTo(0)

            viewModel.addEvent(event.name, event.screenName)
            viewModel.getEvents()

            val emission2 = awaitItem()
            assertThat(emission2.size).isEqualTo(1)
            println(emission2.first())
            println(event)
            assertThat(emission2.first().name).isEqualTo(event.screenName)
            assertThat(emission2.first().screenName).isEqualTo(event.name)
            //Event(id=0, name=screen1, screenName=evento1, date=1745520794967)
            //Event(id=0, name=evento1, screenName=screen1, date=1745520794953)

        }
    }

}