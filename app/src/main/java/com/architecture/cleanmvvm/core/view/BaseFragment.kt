package com.architecture.cleanmvvm.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.architecture.cleanmvvm.core.presenter.BasePresenter

abstract class BaseFragment : Fragment(), BaseView {

    abstract fun getPresenter(): BasePresenter<out BaseView>;

    abstract fun getSetUpView(): Int;

    @Override
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getSetUpView(), container, false)
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getPresenter().onUpdateUi();
        super.onViewCreated(view, savedInstanceState)
    }

    @Override
    override fun onResume() {
        super.onResume()
    }

    @Override
    override fun onPause() {
        super.onPause()
    }

    @Override
    override fun onDestroyView() {
        super.onDestroyView()
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun viewLoaded() {
        // show data in here
    }

    override fun showGenericError(error: String) {
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
    }

    override fun showTechnicalError(error: String) {
        Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
    }

    abstract fun getInstance(): Fragment

}