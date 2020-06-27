package com.architecture.repository.demo.repository

import android.util.Log
import com.architecture.business.core.exception.BusinessException
import com.architecture.cleanmvvm.node1.demo.info.ToDoInfo
import com.architecture.cleanmvvm.node1.demo.repository.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class WeatherCacheImpl(
    val forceRefresh: Boolean,
    val localDataGetting: LocalToDoRepositoryImpl,
    val remoteDataGetting: RemoteToDoRepositoryImpl
) : ToDoRepository {

    var mParam: Int = 0;

    fun shouldFetch(data: ToDoInfo?): Boolean {
        return data == null || forceRefresh
    }

    override fun setParam(param: Int) {
        mParam = param
        localDataGetting.setParam(mParam)
        remoteDataGetting.setParam(mParam)
    }

    override suspend fun invoke(): ToDoInfo {
        var result: ToDoInfo? = null;
        try {
            result = localDataGetting()
        } catch (exception: Throwable) {
            Log.e("vhgiang", exception?.toString())
        }

        if (shouldFetch(result)) {

            CoroutineScope(Dispatchers.Default).async {
                delay(4000) //for show showloading dialog
                result = remoteDataGetting()
            }.await()

            result?.let {
                localDataGetting.saveTodo(result!!)
                result = localDataGetting()
            }

        }

        result?.let {
            return result as ToDoInfo
        }

        throw BusinessException();
    }

}