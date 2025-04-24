package com.frommetoyou.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frommetoyou.domain.use_case.EventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val eventsUseCase: EventsUseCase
) : ViewModel() {

    fun addEvent(screenName: String, event: String) = viewModelScope.launch {
        eventsUseCase.addEvent(screenName, event).collect {
            Log.v("Eventos",it.getOrNull().toString())
        }
    }
}