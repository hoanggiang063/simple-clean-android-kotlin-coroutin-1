package com.architecture.repository.demo.repository

import android.util.Log
import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import com.architecture.repository.demo.mapper.LocalTodoMapper
import com.architecture.repository.demo.mapper.RemoteTodoMapper
import com.architecture.repository.demo.service.Webservice

class RemoteToDoRepositoryImpl(var service: Webservice) : ToDoRepository {

    var myParam: Int = 0;
    override fun setParam(param: Int) {
        this.myParam = param;
    }

    override fun getParam(): Int {
        return myParam;
    }

    override suspend fun getToDo(id: Int): ToDoInfo {

        try {
            return RemoteTodoMapper().transform(service.getTodo(id))
        } catch (error: Throwable) {
            Log.e("vhgiang", "remote error:"+ error?.printStackTrace())
            throw BaseExceptionMapperImpl().transform(error)
        }

    }
}