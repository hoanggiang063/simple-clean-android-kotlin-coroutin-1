package com.architecture.repository.weather.remote.model

class WeatherModel {
    lateinit var city: City
    lateinit var list: List<ForeCast>
}

class City {
    lateinit var id: String
    lateinit var name: String
    lateinit var coord: CityCoord
    lateinit var country: String
    var population: Int = 0
    lateinit var timeZone: String

}

class CityCoord {
    var lon: Long = 0
    var lat: Long = 0
}

class ForeCast {
    var dt: Long = 0
    var sunrise: Long = 0
    var sunset: Long = 0
    lateinit var temp: Temperature
    lateinit var feels_like: FeelsLike
    var pressure: Int = 0
    var humidity: Int = 0
    lateinit var weather: List<Weather>
    var speed: Long = 0
    var deg: Long = 0;
    var clouds: Long = 0;
    var rain: Long = 0;
}


class Temperature {
    var day: Long = 0;
    var min: Long = 0;
    var max: Long = 0;
    var night: Long = 0;
    var eve: Long = 0;
    var morn: Long = 0;
}

class FeelsLike {
    var day: Long = 0;
    var night: Long = 0;
    var eve: Long = 0;
    var morn: Long = 0;
}

class Weather {
    lateinit var id: String
    lateinit var main: String
    lateinit var description: String
    lateinit var icon: String
}
