package com.architecture.cleanmvvm

import android.app.Application
import com.architecture.repository.demo.local.service.AppDatabase
import org.jetbrains.anko.doAsync

class CleanApp: Application() {
    override fun onCreate() {
        super.onCreate()
        doAsync {
            AppDatabase.getInstance(this@CleanApp)
        }
    }
}