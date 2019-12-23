package com.architecture.repository.demo.repository

import android.util.Log
import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import com.architecture.repository.demo.mapper.LocalTodoMapper
import com.architecture.repository.demo.model.ToDoDao

class LocalToDoRepositoryImpl(val todo: ToDoDao) : ToDoRepository {
    var mParam: Int = 0;

    override suspend fun getToDo(id: Int): ToDoInfo {
        try {
            return LocalTodoMapper().transform(todo.getTodoEntity(id))
        } catch (exception: Throwable) {
            Log.e("vhgiang", "local db error: " + exception?.message)
            throw BaseExceptionMapperImpl().transform(exception)
        }
    }

    override fun setParam(param: Int) {
        mParam = param;
    }

    override fun getParam(): Int {
        return mParam
    }

    suspend fun saveTodo(todoObj: ToDoInfo) {
        todo.save(LocalTodoMapper().revert(todoObj))
    }
}