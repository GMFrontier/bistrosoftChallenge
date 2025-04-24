package com.frommetoyou.presentation.ui

import com.frommetoyou.common.factorialBig
import com.frommetoyou.domain.use_case.EventsUseCase
import com.frommetoyou.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class FactorialViewModel @Inject constructor(
    private val eventsUseCase: EventsUseCase
) : BaseViewModel(eventsUseCase) {

    private val _factorial = MutableStateFlow<String>("")
    val factorial: StateFlow<String> = _factorial

    fun calculateFactorial(input: String) {
        _factorial.value = factorialBig(input.toInt()).toString()
    }
}