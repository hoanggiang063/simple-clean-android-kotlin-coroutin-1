package com.architecture.cleanmvvm.node1.demo.callback

import com.architecture.business.core.callback.BasePresentCallBack
import com.architecture.cleanmvvm.node1.demo.info.ToDoInfo

interface ToDoCallBack : BasePresentCallBack<ToDoInfo> {
    override fun onSuccess(expectedResult: ToDoInfo?)
    override fun onFail(throwable: Throwable)
}