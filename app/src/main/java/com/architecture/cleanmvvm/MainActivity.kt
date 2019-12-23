package com.architecture.cleanmvvm

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.architecture.business.demo.callback.ToDoCallBack
import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import com.architecture.business.demo.usecase.ToDoUseCase
import com.architecture.business.demo.usecase.ToDoUseCaseImpl
import com.architecture.repository.demo.local.service.AppDatabase
import com.architecture.repository.demo.repository.CacheToDoRepositoryImpl
import com.architecture.repository.demo.repository.LocalToDoRepositoryImpl
import com.architecture.repository.demo.repository.RemoteToDoRepositoryImpl
import com.architecture.repository.demo.service.Webservice
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toDoRepository: ToDoRepository =
            RemoteToDoRepositoryImpl(RetrofitFactory.makeRetrofitService())
        var todoUseCase: ToDoUseCase = ToDoUseCaseImpl(toDoRepository)

        val database: AppDatabase = AppDatabase.getInstance(this);
        var localRepository: ToDoRepository = LocalToDoRepositoryImpl(database.todoDao())



        btnRemote.setOnClickListener {
            val progress = ProgressDialog(this@MainActivity)
            progress.setTitle("Loading")
            progress.setMessage("Wait while loading...")
            progress.setCancelable(false)
            progress.show()
            // load data by network (remote)
            todoUseCase.buildUseCase(1)
            todoUseCase.executeData(object : ToDoCallBack {
                override fun onSuccess(expectedResult: ToDoInfo?) {
                    Log.v("vhgiang", "=> remote solution run pass: " + expectedResult.toString())
                    progress.dismiss()
                }

                override fun onFail(throwable: Throwable) {
                    Log.v("vhgiang", "=> remote solution run fail: " + throwable?.message)
                    progress.dismiss()
                }

            })
        }

        btnCache.setOnClickListener {
            val progress = ProgressDialog(this@MainActivity)
            progress.setTitle("Loading")
            progress.setMessage("Wait while loading...")
            progress.setCancelable(false)
            progress.show()
            // load data by network (remote)

            var cacheRepository: ToDoRepository = CacheToDoRepositoryImpl(
                true,
                localRepository as LocalToDoRepositoryImpl,
                toDoRepository as RemoteToDoRepositoryImpl
            )

            todoUseCase = ToDoUseCaseImpl(cacheRepository)
            todoUseCase.buildUseCase(1)
            todoUseCase.executeData(object : ToDoCallBack {
                override fun onSuccess(expectedResult: ToDoInfo?) {
                    Log.v("vhgiang", "=> cache solution run pass: " + expectedResult.toString())
                    progress.dismiss()
                }

                override fun onFail(throwable: Throwable) {
                    Log.v("vhgiang", "=> cache solution run fail: " + throwable?.message)
                    progress.dismiss()
                }

            })
        }

        btnLocalDb.setOnClickListener {

            val progress = ProgressDialog(this@MainActivity)
            progress.setTitle("Loading")
            progress.setMessage("Wait while loading...")
            progress.setCancelable(false)
            progress.show()
            // load data by network (remote)


            // load data by db
            todoUseCase = ToDoUseCaseImpl(localRepository)
            todoUseCase.buildUseCase(1)
            todoUseCase.executeData(object : ToDoCallBack {
                override fun onSuccess(expectedResult: ToDoInfo?) {
                    Log.v("vhgiang", "=> db solution run pass: " + expectedResult.toString())

                    progress.dismiss()
                }

                override fun onFail(throwable: Throwable) {
                    Log.v("vhgiang", "=> db solution run fail: " + throwable?.message)

                    progress.dismiss()
                }

            })

        }
    }


}

object RetrofitFactory {
    const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    fun makeRetrofitService(): Webservice {


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY)
                ).build()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build().create(Webservice::class.java)
    }

}
