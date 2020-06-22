package com.architecture.cleanmvvm.core.presenter

interface BasePresenter {
    fun onUpdateUi()
    fun onResume()
    fun onPause()
    fun onDestroyView()
    fun onDestroyed()
}