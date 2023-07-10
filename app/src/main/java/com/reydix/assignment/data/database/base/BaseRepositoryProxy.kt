package com.reydix.assignment.data.database.base

import com.reydix.assignment.data.network.base.ResponseListener
import com.reydix.assignment.data.network.base.ResponseListenerIsAttached
import com.reydix.assignment.data.network.base.BaseResponse
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


abstract class RepositoryProxyGet<T, R>  {

    abstract val dao: BaseDao<T>?

    @Throws(Exception::class)
    abstract fun getItemsFromDb(): List<T>?

    @Throws(Exception::class)
    abstract fun getItemsFromApiAsync(): Deferred<R>?

    abstract fun transformApiItemsToDbItems(response: R, dbItems: List<T>?): List<T>?

    @Throws(Exception::class)
    abstract fun insertItemsOnDb(items: List<T>?)

    fun run(
        forceApi: Boolean,
        callback: ResponseListener<Any?>?,
        error: ((e: Throwable) -> Unit)?,
        AAResponseListenerIsAttached: ResponseListenerIsAttached?
    ): Job {
        return GlobalScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, exception ->
            // Handle exception
            if (AAResponseListenerIsAttached?.isAttached() == true) {
                GlobalScope.launch(Dispatchers.Main) {
                    callback?.run {
                        this.error(exception.message)
                    }

                    error?.run {
                        this(exception)
                    }

                }
            }
        }) {
            var items = getItemsFromDb()
            if (items?.isEmpty() == true || forceApi) {

                val apiItems = getItemsFromApiAsync()?.await()
                if (apiItems is BaseResponse? && apiItems?.isError == 1) {
                    throw Exception(apiItems?.message)
                }

                if (apiItems != null) {
                    items = transformApiItemsToDbItems(apiItems, items)
                    insertItemsOnDb(items)
                }
            }
        }
    }
}



