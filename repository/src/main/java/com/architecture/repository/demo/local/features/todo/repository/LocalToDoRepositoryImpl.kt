package com.architecture.repository.demo.repository

import android.util.Log
import com.architecture.cleanmvvm.node1.demo.info.ToDoInfo
import com.architecture.cleanmvvm.node1.demo.repository.ToDoRepository
import com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import com.architecture.repository.demo.mapper.LocalTodoMapper
import com.architecture.repository.demo.model.ToDoDao

class LocalToDoRepositoryImpl(val todo: ToDoDao) : ToDoRepository {
    var mParam: Int = 0;

    override fun setParam(param: Int) {
        mParam = param;
    }

    override suspend fun invoke(): ToDoInfo {
        try {
            return LocalTodoMapper().transform(todo.getTodoEntity(mParam))
        } catch (exception: Throwable) {
            Log.e("vhgiang", "local db error: " + exception?.message)
            throw BaseExceptionMapperImpl().transform(exception)
        }
    }

    suspend fun saveTodo(todoObj: ToDoInfo) {
        todo.save(LocalTodoMapper().revert(todoObj))
    }
}