package com.frommetoyou.presentation

import androidx.lifecycle.viewModelScope
import com.frommetoyou.domain.model.Event
import com.frommetoyou.domain.use_case.EventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(
    private val eventsUseCase: EventsUseCase
) : BaseViewModel(eventsUseCase) {

    private val _events = MutableStateFlow<List<Event>>(listOf())
    val events: StateFlow<List<Event>> = _events

    private val _screenStats = MutableStateFlow<Map<String, Float>>(emptyMap())
    val screenStats: StateFlow<Map<String, Float>> = _screenStats

    fun getEvents() = viewModelScope.launch {
        eventsUseCase.getEvents().collect { result ->
            result
                .onSuccess {
                    _events.value = result.getOrDefault(listOf())
                    calculateScreenStats()
                }
        }
    }

    fun clearEvents() = viewModelScope.launch {
        eventsUseCase.clearEvents().collect {}
    }

    private fun calculateScreenStats() {
        viewModelScope.launch {
            val total = _events.value.size.toFloat()
                .coerceAtLeast(1f) // evita división por cero

            val counts = _events.value.groupingBy { it.screenName }.eachCount()
            val percentages = counts.mapValues { (it.value / total) * 100f }

            _screenStats.value = percentages
        }
    }
}