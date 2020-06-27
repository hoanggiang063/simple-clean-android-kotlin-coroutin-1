package com.architecture.repository.weather.local.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.architecture.repository.weather.local.model.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {
        val DB_NAME = "weatherdb"
    }

    // DAO
    abstract fun weatherDao(): WeatherDao

    private var sInstance: WeatherDatabase? = null

    @Synchronized
    fun getInstance(context: Context): WeatherDatabase {
        if (sInstance == null) {
            sInstance = Room
                .databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    DB_NAME
                )
                .fallbackToDestructiveMigration()
                .build()
        }
        return sInstance!!
    }
}