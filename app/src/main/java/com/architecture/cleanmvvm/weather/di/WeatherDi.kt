package com.architecture.cleanmvvm.weather.di

import androidx.lifecycle.ViewModel
import com.architecture.cleanmvvm.node1.demo.repository.WeatherRepository
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherUseCase
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherUseCaseImpl
import com.architecture.cleanmvvm.weather.viewmodel.WeatherViewModel
import com.architecture.repository.demo.repository.WeatherCacheImpl
import com.architecture.repository.demo.repository.WeatherLocalImpl
import com.architecture.repository.demo.repository.WeatherRemoteImpl
import com.architecture.repository.demo.service.WeatherRemoteService
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit

val featureWeather = module {

    factory {
        get<Retrofit>().create(WeatherRemoteService::class.java)
    }

    factory {
        WeatherRemoteImpl(get())
    }

    factory {
        WeatherLocalImpl(get())
    }

    factory {
        WeatherCacheImpl(false, get(), get()) as WeatherRepository
    }

    factory {
        WeatherUseCaseImpl(get()) as WeatherUseCase
    }

    viewModel { WeatherViewModel(get()) as ViewModel }
}