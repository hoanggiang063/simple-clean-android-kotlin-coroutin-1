package com.architecture.cleanmvvm.core.view

interface BaseView {
    fun viewLoaded();
    fun showGenericError(error: String);
    fun showTechnicalError(error: String);
}