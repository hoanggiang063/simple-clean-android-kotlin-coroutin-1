package com.architecture.cleanmvvm.node1.demo.usecase

import com.architecture.business.core.usecase.BaseUsecaseImpl
import com.architecture.cleanmvvm.node1.demo.callback.WeatherCallBack
import com.architecture.cleanmvvm.node1.demo.info.WeatherInfo
import com.architecture.cleanmvvm.node1.demo.repository.WeatherRepository

class WeatherUseCaseImpl(weatherRepository: WeatherRepository) :
    WeatherUseCase,
    BaseUsecaseImpl<WeatherRequest, WeatherInfo, WeatherCallBack>(weatherRepository) {

    override fun hanldeExceptionByChild(error: Throwable, callback: WeatherCallBack): Boolean {
        return false;
    }
}