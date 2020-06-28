package com.architecture.cleanmvvm.weather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.architecture.business.core.usecase.BaseUseCase
import com.architecture.cleanmvvm.node1.demo.callback.WeatherCallBack
import com.architecture.cleanmvvm.node1.demo.info.WeatherInfo
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherRequest
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherUseCase
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Job
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.isA


@RunWith(MockitoJUnitRunner::class)
class WeatherViewModelTest {

    @Rule
    @JvmField
    public var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var weatherUseCase: WeatherUseCase

    @Mock
    lateinit var viewModel: WeatherViewModel

    lateinit var mockObserver: Observer<WeatherInfo>

    @Before
    fun setUp(){
            mockObserver = mockObserver<WeatherInfo>()
    }


    @Test
    fun shouldTriggerSuccessWhenUseCaseReturnData(){
        weatherUseCase = object:WeatherUseCase{
            override fun buildUseCase(param: WeatherRequest): BaseUseCase<WeatherRequest, WeatherInfo, WeatherCallBack> = this
            override fun invoke(callback: WeatherCallBack): Job {
                callback.onSuccess(WeatherInfo())
                return Job()
            }
        }
        viewModel = WeatherViewModel(weatherUseCase);
        viewModel.currentWeatherInfo.observe(mockLifecycleOwner(), mockObserver)
        viewModel.loadWeather("cali")
        verify(mockObserver).onChanged(ArgumentMatchers.isA(WeatherInfo::class.java))
    }

    private fun mockLifecycleOwner(): LifecycleOwner {
        val lifecycleOwner: LifecycleOwner = Mockito.mock(LifecycleOwner::class.java)
        val lifecycle = LifecycleRegistry(Mockito.mock(LifecycleOwner::class.java))
        lifecycle.markState(Lifecycle.State.RESUMED)
        Mockito.`when`(lifecycleOwner.lifecycle).thenReturn(lifecycle)
        return lifecycleOwner
    }
    private fun <T> mockObserver(): Observer<T> {
        return Mockito.mock(Observer::class.java) as Observer<T>
    }

    @Test
    fun shouldTriggerDefaultFailWhenUseCaseReturnDefaultFail(){

    }

    @Test
    fun shouldTriggerTechnicalFailWhenUseCaseReturnTechnicalFail(){

    }

    @Test
    fun shouldTriggerBusinessFailWhenUseCaseReturnBusinessFail(){

    }
}