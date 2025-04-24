package com.frommetoyou.domain.use_case

import com.frommetoyou.core.BuildConfig
import com.frommetoyou.domain.model.Dog
import com.frommetoyou.domain.repository.DogsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DogsUseCase @Inject constructor(
    private val dogsRepository: DogsRepository
) {

    fun getRandomDog(breed: String? = null): Flow<Result<Dog>> {
        return breed?.let { dogsRepository.getDogByBreed(it) }
            ?: dogsRepository.getDog()
    }
}