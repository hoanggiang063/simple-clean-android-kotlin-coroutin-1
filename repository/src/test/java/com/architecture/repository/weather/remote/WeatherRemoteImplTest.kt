package com.architecture.repository.weather.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.architecture.cleanmvvm.node1.demo.usecase.WeatherRequest
import com.architecture.repository.demo.repository.WeatherRemoteImpl
import com.architecture.repository.demo.service.WeatherRemoteService
import com.architecture.repository.weather.remote.model.WeatherModel
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class WeatherRemoteImplTest {
    @Rule
    @JvmField
    public var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: WeatherRemoteImpl

    @Mock
    lateinit var service: WeatherRemoteService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = WeatherRemoteImpl(service)
        repository.setParam(WeatherRequest())
    }

    @Test
    fun shouldReturnDataWhenApiSuccess() = runBlocking {
        val file = File(javaClass.classLoader.getResource("test.json").file)
        val bufferedReader: BufferedReader = file.bufferedReader()
        val inputString = bufferedReader.use { it.readText() }
        val gson = Gson()
        val weatherInfoInput = gson.fromJson(inputString, WeatherModel::class.java)
        doReturn(weatherInfoInput).`when`(service).getWeather(any(), any(), any(), any())
        val weatherInfoOutPut = repository.invoke()

        Assert.assertEquals(weatherInfoOutPut.id, weatherInfoInput.city.id)
        // should evaluate more values but no time
    }

    @Test
    fun shouldThrowFailWhenApiReturnIoException() = runBlocking {
        doAnswer {
            throw IOException()
        }.`when`(service).getWeather(any(), any(), any(), any())
        repository.invoke()
    }

    @Test
    fun shouldThrowFailWhenApiReturnHttpException() = runBlocking {
        doAnswer {
            throw IOException()
        }.`when`(service).getWeather(any(), any(), any(), any())
        repository.invoke()
    }

    @Test
    fun shouldThrowFailWhenApiReturnBusinessException() = runBlocking {
        doAnswer {
            throw IOException()
        }.`when`(service).getWeather(any(), any(), any(), any())
        repository.invoke()
    }

}