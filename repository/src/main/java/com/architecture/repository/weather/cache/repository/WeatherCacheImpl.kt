package com.architecture.repository.demo.repository

import android.util.Log
import com.architecture.business.core.exception.BusinessException
import com.architecture.cleanmvvm.node1.demo.info.WeatherInfo
import com.architecture.cleanmvvm.node1.demo.repository.WeatherRepository
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherRequest
import com.architecture.repository.core.model.Undefine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class WeatherCacheImpl(
    val forceRefresh: Boolean,
    val localDataGetting: WeatherLocalImpl,
    val remoteDataGetting: WeatherRemoteImpl
) : WeatherRepository {

    fun shouldFetch(data: WeatherInfo?): Boolean {
        return data == null || Undefine.UNDEFINE_STRING.equals(data.id) || forceRefresh
    }

    override fun setParam(param: WeatherRequest) {
        localDataGetting.setParam(param)
        remoteDataGetting.setParam(param)
    }

    override suspend fun invoke(): WeatherInfo {
        var result: WeatherInfo? = null;
        try {
            result = localDataGetting()
        } catch (exception: Throwable) {
            Log.e("vhgiang", exception?.toString())
        }

        if (shouldFetch(result)) {

            CoroutineScope(Dispatchers.Default).async {
                result = remoteDataGetting()
            }.await()

            result?.let {
                localDataGetting.saveWeather(result!!)
                result = localDataGetting()
            }

        }

        result?.let {
            return result as WeatherInfo
        }

        throw BusinessException();
    }

}