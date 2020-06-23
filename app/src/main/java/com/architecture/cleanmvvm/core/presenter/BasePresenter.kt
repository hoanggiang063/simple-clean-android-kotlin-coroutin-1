package com.architecture.cleanmvvm.core.presenter

import com.architecture.cleanmvvm.core.view.BaseView

interface BasePresenter<T : BaseView> {
    fun onUpdateUi()
    fun onResume()
    fun onPause()
    fun onDestroyView()
    fun onDestroyed()
}