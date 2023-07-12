package com.example.unlimintjokes.data.api

import com.example.unlimintjokes.domain.model.JokeModel
import retrofit2.Response
import retrofit2.http.GET

interface JokesApi {
    @GET("/api?format=json")
    suspend fun getJoke(): Response<JokeModel>
}
