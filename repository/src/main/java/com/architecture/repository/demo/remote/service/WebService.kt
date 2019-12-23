package com.architecture.repository.demo.service

import com.architecture.repository.demo.model.TodoEntity
import com.architecture.repository.demo.model.TodoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservice {
    @GET("/todos/{id}")
    suspend fun getTodo(@Path(value = "id") todoId: Int): TodoModel
}