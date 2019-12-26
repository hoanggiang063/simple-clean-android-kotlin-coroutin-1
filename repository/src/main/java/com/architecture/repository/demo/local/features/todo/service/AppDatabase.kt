package com.architecture.repository.demo.local.features.todo.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.architecture.repository.demo.model.ToDoDao
import com.architecture.repository.demo.model.TodoEntity

@Database(entities = [TodoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    // DAO
    abstract fun todoDao(): ToDoDao

    companion object {

        private var sInstance: AppDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "example")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return sInstance!!
        }
    }
}