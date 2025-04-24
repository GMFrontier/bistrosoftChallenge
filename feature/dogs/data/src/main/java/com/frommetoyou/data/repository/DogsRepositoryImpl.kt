package com.frommetoyou.data.repository

import com.frommetoyou.common.CoroutinesDispatcherProvider
import com.frommetoyou.common.parseResponse
import com.frommetoyou.data.service.DogsApiService
import com.frommetoyou.domain.model.Dog
import com.frommetoyou.domain.repository.DogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    private val dogsApiService: DogsApiService,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : DogsRepository {
    override fun getDog(): Flow<Result<Dog>> = flow {
        emit(dogsApiService.getRandomDog())
    }
        .map { result ->
            val dog = result.parseResponse()
            dog.map {
                Dog(image = it.message)
            }
        }
        .catch { error -> emit(Result.failure(error)) }
        .flowOn(coroutinesDispatcherProvider.io)

    override fun getDogByBreed(breed: String): Flow<Result<Dog>> = flow {
        emit(dogsApiService.getRandomDogByBreed(breed))
    }
        .map { result ->
            val dog = result.parseResponse()
            dog.map {
                Dog(image = it.message)
            }
        }
        .catch { error -> emit(Result.failure(error)) }
        .flowOn(coroutinesDispatcherProvider.io)
}