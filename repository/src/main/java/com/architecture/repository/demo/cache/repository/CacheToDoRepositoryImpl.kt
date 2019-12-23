package com.architecture.repository.demo.repository

import android.util.Log
import com.architecture.business.core.exception.BusinessException
import com.architecture.business.demo.info.ToDoInfo
import com.architecture.business.demo.repository.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class CacheToDoRepositoryImpl(
    val forceRefresh: Boolean,
    val local: LocalToDoRepositoryImpl,
    val remote: RemoteToDoRepositoryImpl
) : ToDoRepository {

    var mParam: Int = 0;

    override suspend fun getToDo(id: Int): ToDoInfo {
        var result: ToDoInfo? = null;
        try {
            result = local.getToDo(id)
        } catch (exception: Throwable) {
            Log.e("vhgiang", exception?.toString())
        }

        if (shouldFetch(result)) {

            CoroutineScope(Dispatchers.Default).async {
                delay(4000) //for show showloading dialog
                result = remote.getToDo(id)
            }.await()

            result?.let {
                local.saveTodo(result!!)
                result = local.getToDo(id)
            }

        }

        result?.let {
            return result as ToDoInfo
        }

        throw BusinessException();
    }

    fun shouldFetch(data: ToDoInfo?): Boolean {
        return data == null || forceRefresh
    }

    override fun setParam(param: Int) {
        mParam = param
    }

    override fun getParam(): Int {
        return mParam
    }

}