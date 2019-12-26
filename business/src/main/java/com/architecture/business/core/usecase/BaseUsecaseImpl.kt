package com.architecture.business.core.usecase

import com.architecture.business.core.callback.BasePresentCallBack
import com.architecture.business.core.repository.BaseRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseUsecaseImpl<Param, Result, CallBack : BasePresentCallBack<Result>>
    (val bankRepository: BaseRepository<Param, Result>)
    :BaseUseCase<Param, Result, CallBack> {

    var subscriberContext: CoroutineContext = Dispatchers.IO
    var observerContext: CoroutineContext = Dispatchers.Main

    override fun buildUseCase(param: Param): BaseUsecaseImpl<Param, Result, CallBack> {
        bankRepository.setParam(param)
        return this
    }

    abstract suspend fun getData(): Result

    override fun invoke(callback: CallBack): Job {
        return CoroutineScope(subscriberContext).launch {
            // implement thread to call
            var data: Result?

            try {
                data = getData()
                withContext(observerContext) {
                    handleSuccess(data, callback)
                }
            } catch (error: Throwable) {
                withContext(observerContext) {
                    handleFail(error, callback)
                }

            }

        }
    }

    private fun handleSuccess(result: Result?, callback: CallBack) {
        callback.onSuccess(result)
    }

    private fun handleFail(error: Throwable, callback: CallBack) {

        if (!handleByChild(error, callback)) {
            handleByGeneric(error, callback)
        } else {
            callback.onFail(error)
        }
    }

    open fun handleByChild(error: Throwable, callback: CallBack): Boolean {
        return false;
    }

    open fun handleByGeneric(error: Throwable, callback: CallBack) {
        callback.onFail(error)
    }
}



