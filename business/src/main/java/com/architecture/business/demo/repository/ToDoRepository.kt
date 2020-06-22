package com.architecture.cleanmvvm.node1.demo.repository

import com.architecture.business.core.repository.BaseRepository
import com.architecture.cleanmvvm.node1.demo.info.ToDoInfo

interface ToDoRepository : BaseRepository<Int, ToDoInfo> {
}