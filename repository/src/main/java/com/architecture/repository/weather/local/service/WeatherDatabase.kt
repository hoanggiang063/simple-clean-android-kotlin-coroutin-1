package com.architecture.repository.weather.local.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.architecture.repository.weather.local.model.WeatherEntity
import com.architecture.repository.weather.local.model.WeatherItemEntity

@Database(
    entities = [WeatherEntity::class, WeatherItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "CleanDB.db"
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, DB_NAME)
                .build()
    }

    // DAO
    abstract fun weatherDao(): WeatherDao
}