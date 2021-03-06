package com.architecture.repository.demo.mapper

import com.architecture.business.demo.info.ToDoInfo
import com.architecture.repository.core.mapper.BaseInfoMapper
import com.architecture.repository.demo.model.TodoEntity

class CacheTodoMapper : BaseInfoMapper<TodoEntity, ToDoInfo> {
    override fun transform(input: TodoEntity): ToDoInfo {
        var info: ToDoInfo = ToDoInfo()
        info.id = input.id
        info.completed = input.completed
        info.title = input.title
        return info
    }

}
