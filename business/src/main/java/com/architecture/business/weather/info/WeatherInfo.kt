package com.architecture.cleanmvvm.node1.demo.info

class WeatherInfo {
    lateinit var id: String
    lateinit var cityName: String
    var lat: Long = 0
    var long: Long = 0
    lateinit var county: String
    lateinit var timeZone: String
    var foreCastItems: List<WeatherItemInfo>? = null
}

class WeatherItemInfo {
    var id: Long = 0
    var date: Long = 0
    var temperature: Long = 0
    var pressure: Int = 0
    var humanity: Int = 0
    lateinit var description: String
}