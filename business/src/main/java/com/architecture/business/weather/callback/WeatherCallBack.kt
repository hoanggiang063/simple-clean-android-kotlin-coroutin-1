package com.architecture.cleanmvvm.node1.demo.callback

import com.architecture.business.core.callback.BasePresentCallBack
import com.architecture.cleanmvvm.node1.demo.info.WeatherInfo

interface WeatherCallBack : BasePresentCallBack<WeatherInfo> {
    override fun onSuccess(expectedResult: WeatherInfo?)
    override fun onFail(throwable: Throwable)
}