package com.example.unlimintjokes.domain.repository

import com.example.unlimintjokes.domain.model.JokeModel
import com.example.unlimintjokes.utils.network.DataResult
import kotlinx.coroutines.flow.Flow

interface JokesRepo {
    suspend fun getJoke(): Flow<DataResult<JokeModel>>

    suspend fun getSavedJokes(): List<JokeModel>

    suspend fun saveJokes(jokes: List<JokeModel>)

    suspend fun deleteAllJokes()
}
