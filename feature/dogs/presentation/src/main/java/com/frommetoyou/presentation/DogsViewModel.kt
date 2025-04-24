package com.frommetoyou.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.frommetoyou.domain.model.Dog
import com.frommetoyou.domain.model.Dog.Companion.getDefaultDog
import com.frommetoyou.domain.use_case.DogsUseCase
import com.frommetoyou.domain.use_case.EventsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val eventsUseCase: EventsUseCase,
    private val dogsUseCase: DogsUseCase
) : BaseViewModel(eventsUseCase) {

    private val _dog = MutableStateFlow<Dog>(getDefaultDog())
    val dog: StateFlow<Dog> = _dog

    private val _breed = MutableStateFlow<String?>(null)
    val breed: StateFlow<String?> = _breed

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading

    fun getRandomDog() = viewModelScope.launch {
        _loading.value = true
        dogsUseCase.getRandomDog(_breed.value).collect { result ->
            _loading.value = false
            result
                .onSuccess {
                    _dog.value = result.getOrDefault(getDefaultDog())
                }
                .onFailure {

                }
        }
    }

    fun setBreed(breed: String?) {
        _breed.value = breed
    }
}