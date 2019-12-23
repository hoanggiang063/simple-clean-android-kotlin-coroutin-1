package com.architecture.repository.demo.model

import androidx.room.Entity

@Entity
data class TodoModel(
    val id: Int = 0,
    val title: String = "",
    val completed: Boolean = false
)