package com.architecture.cleanmvvm.node1.demo.usecase

import com.architecture.business.core.usecase.BaseUsecaseImpl
import com.architecture.cleanmvvm.node1.demo.callback.ToDoCallBack
import com.architecture.cleanmvvm.node1.demo.info.ToDoInfo
import com.architecture.cleanmvvm.node1.demo.repository.ToDoRepository

class ToDoUseCaseImpl(bankRepository: ToDoRepository) :
    ToDoUseCase, BaseUsecaseImpl<Int, ToDoInfo, ToDoCallBack>(bankRepository) {

    override fun hanldeExceptionByChild(error: Throwable, callback: ToDoCallBack): Boolean {
        return false;
    }

}