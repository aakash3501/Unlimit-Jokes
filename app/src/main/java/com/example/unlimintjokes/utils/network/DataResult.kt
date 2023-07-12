package com.example.unlimintjokes.utils.network

sealed interface DataResult<out R> {

    data class Success<out T>(val data: T) : DataResult<T>

    data class Error(val error: String) :
        DataResult<Nothing>
}
