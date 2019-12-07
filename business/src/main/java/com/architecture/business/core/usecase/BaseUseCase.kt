package com.architecture.business.core.usecase

import com.architecture.business.core.callback.BasePresentCallBack
import kotlinx.coroutines.Job

interface BaseUseCase<Param, Result, CallBack : BasePresentCallBack<Result>> {

    fun buildUseCase(param: Param): BaseUsecaseImpl<Param, Result, CallBack>

    fun executeData(callback: CallBack): Job
}