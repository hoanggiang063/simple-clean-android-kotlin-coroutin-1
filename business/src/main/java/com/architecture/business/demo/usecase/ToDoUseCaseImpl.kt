package com.architecture.business.demo.usecase

import com.architecture.business.core.usecase.BaseUsecaseImpl
import com.architecture.business.demo.callback.ToDoCallBack
import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository

class ToDoUseCaseImpl(bankRepository: ToDoRepository) :
    ToDoUseCase, BaseUsecaseImpl<Int, ToDoInfo, ToDoCallBack>(bankRepository) {

    override suspend fun getData(): ToDoInfo {
        var repo = bankRepository as ToDoRepository
        return repo.getToDo(bankRepository.getParam())
    }

}