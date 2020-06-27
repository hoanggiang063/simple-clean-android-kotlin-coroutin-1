package com.architecture.cleanmvvm.node1.demo.info

import com.architecture.business.core.info.Undefine

data class WeatherInfo(

    var id: String = Undefine.UNDEFINE_STRING,
    var cityName: String = Undefine.UNDEFINE_STRING,
    var lat: Long = Undefine.UNDEFINE_LONG,
    var long: Long = Undefine.UNDEFINE_LONG,
    var county: String = Undefine.UNDEFINE_STRING,
    var timeZone: String = Undefine.UNDEFINE_STRING,
    var foreCastItems: List<WeatherItemInfo>? = null
)

data class WeatherItemInfo(
    var id: String = Undefine.UNDEFINE_STRING,
    var date: Long = Undefine.UNDEFINE_LONG,
    var temperature: Long = Undefine.UNDEFINE_LONG,
    var pressure: Int = Undefine.UNDEFINE_INT,
    var humanity: Int = Undefine.UNDEFINE_INT,
    var description: String = Undefine.UNDEFINE_STRING
)