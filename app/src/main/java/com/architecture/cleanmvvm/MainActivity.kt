package com.architecture.cleanmvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.architecture.business.demo.callback.ToDoCallBack
import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import com.architecture.business.demo.usecase.ToDoUseCase
import com.architecture.business.demo.usecase.ToDoUseCaseImpl
import com.architecture.repository.demo.repository.TodoRepositoryImpl
import com.architecture.repository.demo.service.Webservice
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toDoRepository: ToDoRepository = TodoRepositoryImpl(RetrofitFactory.makeRetrofitService())
        var todoUseCase:ToDoUseCase = ToDoUseCaseImpl(toDoRepository)
        todoUseCase.buildUseCase(1)
        todoUseCase.executeData(object : ToDoCallBack {
            override fun onSuccess(expectedResult: ToDoInfo?) {
                Log.v("vhgiang","pass"+ expectedResult!!.title)
            }

            override fun onFail(throwable: Throwable) {
                Log.v("vhgiang","pass"+ throwable?.message)
            }

        })
    }
}

object RetrofitFactory {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun makeRetrofitService(): Webservice {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(Webservice::class.java)
    }

}
