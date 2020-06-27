package com.architecture.cleanmvvm.weather.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.architecture.cleanmvvm.R
import com.architecture.cleanmvvm.node1.demo.info.WeatherItemInfo

class WeatherAdapter(private val info: List<WeatherItemInfo>) :
    RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WeatherViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.view_weather_item, parent, false)
    )

    override fun getItemCount(): Int = info.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) =
        holder.bindTo(info[position])
}

class WeatherViewHolder(val parent: View) : RecyclerView.ViewHolder(parent) {

    val tvWeatherDate: AppCompatTextView by lazy {
        parent!!.findViewById<AppCompatTextView>(R.id.tvWeatherDate)
    }

    val tvWeatherTemperature: AppCompatTextView by lazy {
        parent!!.findViewById<AppCompatTextView>(R.id.tvWeatherTemperature)
    }

    val tvWeatherPressure: AppCompatTextView by lazy {
        parent!!.findViewById<AppCompatTextView>(R.id.tvWeatherPressure)
    }

    val tvWeatherHumidity: AppCompatTextView by lazy {
        parent!!.findViewById<AppCompatTextView>(R.id.tvWeatherHumidity)
    }

    val tvWeatherDescription: AppCompatTextView by lazy {
        parent!!.findViewById<AppCompatTextView>(R.id.tvWeatherDescription)
    }

    val tvWeatherContainer: LinearLayoutCompat by lazy {
        parent!!.findViewById<LinearLayoutCompat>(R.id.lnWeatherContainer)
    }

    fun bindTo(item: WeatherItemInfo) {
        val date: String = String.format(
            parent.context.getString(R.string.weatherItemDate),
            item.date.toString()
        )
        tvWeatherDate.text = date

        val temperature: String = String.format(
            parent.context.getString(R.string.weatherItemTemperature),
            item.temperature.toString()
        )
        tvWeatherTemperature.text = temperature

        val pressure: String = String.format(
            parent.context.getString(R.string.weatherItemPressure),
            item.pressure.toString()
        )
        tvWeatherPressure.text = pressure

        val humanity: String = String.format(
            parent.context.getString(R.string.weatherItemHumidity),
            item.humanity.toString()
        )
        tvWeatherHumidity.text = humanity

        val description: String = String.format(
            parent.context.getString(R.string.weatherItemDescription),
            item.date.toString()
        )
        tvWeatherDescription.text = description

        tvWeatherContainer.contentDescription = String.format(
            "%. %. %. %. %.",
            date, temperature, pressure, humanity, description
        )
    }
}