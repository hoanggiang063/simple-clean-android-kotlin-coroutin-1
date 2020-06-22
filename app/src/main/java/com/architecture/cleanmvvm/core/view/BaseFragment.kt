package com.architecture.cleanmvvm.core.view

import androidx.fragment.app.Fragment

class BaseFragment : Fragment(), BaseView {
    override fun viewLoaded() {
        TODO("Not yet implemented")
    }

    override fun showGenericError(error: String) {
        TODO("Not yet implemented")
    }

    override fun showTechnicalError(error: String) {
        TODO("Not yet implemented")
    }

}