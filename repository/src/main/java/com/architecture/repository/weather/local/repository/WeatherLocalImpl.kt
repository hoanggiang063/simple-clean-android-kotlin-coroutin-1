package com.architecture.repository.demo.repository

import android.util.Log
import com.architecture.cleanmvvm.node1.demo.info.WeatherInfo
import com.architecture.cleanmvvm.node1.demo.info.WeatherItemInfo
import com.architecture.cleanmvvm.node1.demo.repository.WeatherRepository
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherRequest
import com.architecture.repository.core.mapper.BaseExceptionMapperImpl
import com.architecture.repository.core.mapper.BaseInfoMapper
import com.architecture.repository.weather.local.model.WeatherEntity
import com.architecture.repository.weather.local.model.WeatherItemEntity
import com.architecture.repository.weather.local.model.WeatherWithDetail
import com.architecture.repository.weather.local.service.WeatherDao

class WeatherLocalImpl(val dao: WeatherDao) : WeatherRepository {

    var request: WeatherRequest? = null;

    override fun setParam(param: WeatherRequest) {
        request = param;
    }

    override suspend fun invoke(): WeatherInfo {
        try {
            var weatherWithDetails = dao.getWeatherWithFullDetail(request!!.city);
            if (weatherWithDetails != null && weatherWithDetails.isNotEmpty()) {
                return WeatherMapper().transform(weatherWithDetails[0])
            } else {
                return WeatherInfo();
            }

        } catch (exception: Throwable) {
            Log.e("vhgiang", "local db error: " + exception?.message)
            throw BaseExceptionMapperImpl().transform(exception)
        }
    }

    suspend fun saveWeather(weatherInfo: WeatherInfo) {
        dao.saveWeather(WeatherMapper().revertWeather(weatherInfo))
        val weatherItems = WeatherMapper().revertWeatherItem(weatherInfo.foreCastItems);
        weatherItems.forEach { item ->
            dao.saveWeatherItem(item)
        }

    }
}

class WeatherMapper : BaseInfoMapper<WeatherWithDetail, WeatherInfo> {
    override fun transform(input: WeatherWithDetail): WeatherInfo {
        val weatherInfo = WeatherInfo();

        weatherInfo.id = input.weather.id;
        weatherInfo.timeZone = input.weather.timeZone
        weatherInfo.long = input.weather.long
        weatherInfo.lat = input.weather.lat
        weatherInfo.county = input.weather.county
        weatherInfo.cityName = input.weather.cityName
        weatherInfo.foreCastItems = getWeatherItem(input.items)

        return weatherInfo;
    }

    private fun getWeatherItem(items: List<WeatherItemEntity>): List<WeatherItemInfo>? {
        val outList = emptyList<WeatherItemInfo>()
        items.forEach { inItem ->
            val outItem = WeatherItemInfo();
            outItem.id = inItem.id
            outItem.description = inItem.description
            outItem.temperature = inItem.temperature
            outItem.date = inItem.date
            outItem.humanity = inItem.humanity
            outItem.pressure = inItem.pressure
        }
        return outList
    }

    fun revertWeather(input: WeatherInfo): WeatherEntity {
        var weatherEntity: WeatherEntity = WeatherEntity()

        weatherEntity.id = input.id
        weatherEntity.lat = input.lat
        weatherEntity.long = input.long
        weatherEntity.cityName = input.cityName
        weatherEntity.county = input.county
        weatherEntity.timeZone = input.timeZone

        return weatherEntity
    }

    fun revertWeatherItem(items: List<WeatherItemInfo>?): List<WeatherItemEntity> {
        val outList = emptyList<WeatherItemEntity>()
        items?.forEach { inItem ->
            val outItem = WeatherItemEntity();
            outItem.id = inItem.id
            outItem.description = inItem.description
            outItem.temperature = inItem.temperature
            outItem.date = inItem.date
            outItem.humanity = inItem.humanity
            outItem.pressure = inItem.pressure
        }
        return outList
    }

}
