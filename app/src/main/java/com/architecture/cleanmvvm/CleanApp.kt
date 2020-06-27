package com.architecture.cleanmvvm

import android.app.Application
import com.architecture.cleanmvvm.core.di.appComponent
import com.architecture.repository.weather.local.service.WeatherDatabase
import org.jetbrains.anko.doAsync
import org.koin.android.ext.android.startKoin

class CleanApp : Application() {
    override fun onCreate() {
        super.onCreate()
        doAsync {
            WeatherDatabase.getInstance(this@CleanApp)
        }
        configureDi()
    }

    // CONFIGURATION ---
    open fun configureDi() =
        startKoin(this, provideComponent())

    // PUBLIC API ---
    open fun provideComponent() = appComponent
}