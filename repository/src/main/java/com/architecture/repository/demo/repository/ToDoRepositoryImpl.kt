package com.architecture.repository.demo.repository

import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import com.architecture.repository.demo.mapper.TodoMapper
import com.architecture.repository.demo.service.Webservice

class TodoRepositoryImpl(var service: Webservice) : ToDoRepository {

    var myParam: Int = 0;

    override suspend fun getToDo(id: Int): ToDoInfo {
        return TodoMapper().transform(service.getTodo(id))
    }

    override fun setParam(param: Int) {
        this.myParam = param;
    }

    override fun getParam(): Int {
        return myParam;
    }
}