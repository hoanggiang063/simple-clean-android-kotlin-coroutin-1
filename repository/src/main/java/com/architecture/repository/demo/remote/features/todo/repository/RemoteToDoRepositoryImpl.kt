package com.architecture.repository.demo.repository

import android.util.Log
import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import com.architecture.repository.demo.mapper.RemoteTodoMapper
import com.architecture.repository.demo.service.Webservice

class RemoteToDoRepositoryImpl(var service: Webservice) : ToDoRepository {

    var myParam: Int = 0;
    override fun setParam(param: Int) {
        this.myParam = param;
    }

    override suspend fun invoke(): ToDoInfo {
        try {
            return RemoteTodoMapper().transform(service.getTodo(myParam))
        } catch (error: Throwable) {
            Log.e("vhgiang", "remote error:" + error?.printStackTrace())
            throw BaseExceptionMapperImpl().transform(error)
        }
    }
}