package com.architecture.cleanmvvm.core.presenter

import com.architecture.cleanmvvm.core.view.BaseView
import kotlinx.coroutines.Job

class BasePresenterImpl<T : BaseView>(var view: BaseView) : BasePresenter<T> {

    var listTask: Array<Job> = emptyArray<Job>();

    override fun onUpdateUi() {
        view.viewLoaded();
    }

    override fun onResume() {
        // open for future use
    }

    override fun onPause() {
        // open for future use
    }

    override fun onDestroyView() {
        cancelTasks();
    }

    override fun onDestroyed() {
        // open for future use
    }

    private fun cancelTasks() {
        if (listTask.isNotEmpty()) {
            listTask.forEach {
                it.cancel();
            }
        }
    }

}