package com.example.gronurgrocery.common.network

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T?): Resource<T>(data)
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: String): Resource<T>()
}