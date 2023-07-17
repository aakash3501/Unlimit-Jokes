package com.example.unlimintjokes.data.repoImplementation

import com.example.unlimintjokes.data.api.JokesApi
import com.example.unlimintjokes.database.AppDataBase
import com.example.unlimintjokes.domain.model.JokeModel
import com.example.unlimintjokes.domain.repository.JokesRepo
import com.example.unlimintjokes.utils.network.DataResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class JokesRepoImpl : JokesRepo, KoinComponent {
    private val jokesApi: JokesApi by inject()
    private val appDataBase: AppDataBase by inject()
    override suspend fun getJoke(): Flow<DataResult<JokeModel>> {
        return callbackFlow {
            val result = jokesApi.getJoke()
            if (result.isSuccessful) {
                result.body()?.let { trySend(DataResult.Success(it)) }
            } else {
                trySend(DataResult.Error(result.message()))
            }
            awaitClose()
        }
    }

    override suspend fun getSavedJokes(): List<JokeModel> {
        return appDataBase.jokesDao().getAllJokes()
    }

    override suspend fun saveJokes(jokes: List<JokeModel>) {
        appDataBase.jokesDao().insertJokes(jokes)
    }

    override suspend fun deleteAllJokes() {
        appDataBase.jokesDao().deleteAllJokes()
    }
}
