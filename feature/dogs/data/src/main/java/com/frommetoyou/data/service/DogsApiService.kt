package com.frommetoyou.data.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApiService {

    @GET("breeds/image/random/")
    suspend fun getRandomDog() : Response<DogResponse>

    @GET("breed/{breed}/images/random/")
    suspend fun getRandomDogByBreed(
        @Path("breed") breed: String
    ) : Response<DogResponse>
}