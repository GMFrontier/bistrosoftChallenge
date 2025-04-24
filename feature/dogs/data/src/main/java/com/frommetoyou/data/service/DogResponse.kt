package com.frommetoyou.data.service

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("message")
    val message: String = "",
    @SerializedName("status")
    val status: String
)