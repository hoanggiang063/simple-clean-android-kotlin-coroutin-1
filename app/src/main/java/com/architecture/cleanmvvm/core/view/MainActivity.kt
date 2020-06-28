package com.architecture.cleanmvvm.core.view

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.WindowManager
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val configuration = Configuration(newConfig)
        adjustFontScale(configuration)
    }

    private fun adjustFontScale(configuration: Configuration) {
        val metrics = resources.displayMetrics
        val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(metrics)
        metrics.scaledDensity = configuration.fontScale * metrics.density
        baseContext.resources.updateConfiguration(configuration, metrics)
    }
}
