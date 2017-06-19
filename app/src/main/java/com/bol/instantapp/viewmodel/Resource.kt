package com.bol.instantapp.viewmodel

import com.bol.instantapp.exception.AppException
import com.bol.instantapp.viewmodel.Resource.Status.ERROR
import com.bol.instantapp.viewmodel.Resource.Status.LOADING
import com.bol.instantapp.viewmodel.Resource.Status.SUCCESS

class Resource<T> private constructor(val status: Resource.Status, val data: T?, val exception: AppException?) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(exception: AppException?): Resource<T> {
            return Resource(ERROR, null, exception)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}