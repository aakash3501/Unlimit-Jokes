package com.example.unlimintjokes.utils.network

import com.example.unlimintjokes.data.api.JokesApi
import retrofit2.Retrofit

object ApiHelper {
    fun getJokeApi(retrofit: Retrofit): JokesApi {
        return retrofit.create(JokesApi::class.java)
    }
}
