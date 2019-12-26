package com.architecture.repository.demo.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class ToDoDao() {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insert(todo: TodoEntity)

    @Query("SELECT * FROM TodoEntity WHERE id = :id LIMIT 1")
    abstract suspend fun getTodoEntity(id: Int): TodoEntity

    suspend fun save(todoEntity: TodoEntity) {
        insert(todoEntity)
    }
}