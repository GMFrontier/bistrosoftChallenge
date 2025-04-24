package com.frommetoyou.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class Event(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val screenName: String,
    val date: String,
)
