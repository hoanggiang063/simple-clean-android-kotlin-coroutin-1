package com.architecture.repository.weather.local.model

import androidx.room.*
import com.architecture.repository.core.model.Undefine

@Entity(tableName = "WeatherEntity")
data class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = Undefine.UNDEFINE_STRING,

    @ColumnInfo(name = "cityName")
    var cityName: String = Undefine.UNDEFINE_STRING,

    @ColumnInfo(name = "lat")
    var lat: Long = Undefine.UNDEFINE_LONG,

    @ColumnInfo(name = "long")
    var long: Long = Undefine.UNDEFINE_LONG,

    @ColumnInfo(name = "county")
    var county: String = Undefine.UNDEFINE_STRING,

    @ColumnInfo(name = "timeZone")
    var timeZone: String = Undefine.UNDEFINE_STRING
)

@Entity(tableName = "WeatherItemEntity")
data class WeatherItemEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: String = Undefine.UNDEFINE_STRING,

    @ColumnInfo(name = "parent_id")
    var parentId: Long = Undefine.UNDEFINE_LONG,

    @ColumnInfo(name = "date")
    var date: Long = Undefine.UNDEFINE_LONG,

    @ColumnInfo(name = "temperature")
    var temperature: Long = Undefine.UNDEFINE_LONG,

    @ColumnInfo(name = "pressure")
    var pressure: Int = Undefine.UNDEFINE_INT,

    @ColumnInfo(name = "humanity")
    var humanity: Int = Undefine.UNDEFINE_INT,

    @ColumnInfo(name = "description")
    var description: String = Undefine.UNDEFINE_STRING
)

data class WeatherWithDetail(
    @Embedded val weather: WeatherEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parent_id"
    )
    val items: List<WeatherItemEntity>
)