package com.example.unlimintjokes.data.repoImplementation

import android.util.Log
import com.example.unlimintjokes.data.api.JokesApi
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
    override suspend fun getJoke(): Flow<DataResult<JokeModel>> {
        return callbackFlow {
            val result = jokesApi.getJoke()
            Log.d("Data------------", result.toString())
            if (result.isSuccessful) {
                result.body()?.let { trySend(DataResult.Success(it)) }
            } else {
                trySend(DataResult.Error(result.message()))
            }
            awaitClose()
        }
    }
}
