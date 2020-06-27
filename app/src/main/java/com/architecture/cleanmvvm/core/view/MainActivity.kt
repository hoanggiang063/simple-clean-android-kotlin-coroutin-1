package com.architecture.cleanmvvm.core.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.architecture.cleanmvvm.R
import com.architecture.cleanmvvm.weather.view.WeatherFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        hostFragment(WeatherFragment());
    }

    private fun hostFragment(fragment: Fragment) {
        if (supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) == null) {
            var fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment, fragment.javaClass.simpleName)
            fragmentTransaction.commit()
        }
    }
}
