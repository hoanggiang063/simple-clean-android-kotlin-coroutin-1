package com.architecture.cleanmvvm.node1.demo.usecase

import com.architecture.business.core.usecase.BaseUseCase
import com.architecture.cleanmvvm.node1.demo.callback.ToDoCallBack
import com.architecture.cleanmvvm.node1.demo.info.ToDoInfo

interface ToDoUseCase : BaseUseCase<Int, ToDoInfo, ToDoCallBack> {
}