package com.frommetoyou.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.frommetoyou.data.local.EventDao
import com.frommetoyou.domain.model.Event

const val DATABASE = "Database"

@Database(entities = [Event::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun eventDao(): EventDao
}