package com.architecture.cleanmvvm.core.di

import com.architecture.cleanmvvm.CleanApp
import com.architecture.cleanmvvm.core.Constants
import com.architecture.cleanmvvm.core.configuration.EnvConfiguration
import com.architecture.cleanmvvm.core.security.SecurityMonitor
import com.architecture.repository.weather.local.service.WeatherDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val DATABASE = "DATABASE"
val repositoryModule = module {
    single {
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build() as OkHttpClient
    }

    single {
        val config: EnvConfiguration = get()
        Retrofit.Builder()
            .baseUrl(config.getEnvironmentUrl())
            .client(
                get()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build() as Retrofit
    }

    single(DATABASE) {
        WeatherDatabase.buildDatabase(androidContext())
    }
    factory {
        (get(DATABASE) as WeatherDatabase).weatherDao()
    }


}