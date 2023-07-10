package com.reydix.assignment.data.network.base

import androidx.annotation.CallSuper

interface ResponseListenerIsAttached {
    fun isAttached(): Boolean
}

abstract class ResponseListener<T> {
    @CallSuper
    open fun success(t: T) {
    }

    @CallSuper
    open fun error(error: String?) {
    }
}