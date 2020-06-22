package com.architecture.repository.demo.mapper

import com.architecture.cleanmvvm.node1.demo.info.ToDoInfo
import com.architecture.repository.core.mapper.BaseInfoMapper
import com.architecture.repository.demo.model.TodoEntity

class LocalTodoMapper : BaseInfoMapper<TodoEntity, ToDoInfo> {
    override fun transform(input: TodoEntity): ToDoInfo {
        var info: ToDoInfo = ToDoInfo()
        info.id = input.id
        info.completed = input.completed
        info.title = input.title
        return info
    }

    fun revert(input: ToDoInfo): TodoEntity {
        var info: TodoEntity = TodoEntity()
        info.id = input.id
        info.completed = input.completed
        info.title = input.title
        return info
    }

}
