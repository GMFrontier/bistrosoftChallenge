package com.frommetoyou.domain.repository

import com.frommetoyou.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {
    fun getDog(): Flow<Result<Dog>>
    fun getDogByBreed(breed: String): Flow<Result<Dog>>
}