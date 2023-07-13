package com.example.unlimintjokes.domain.usecase

import com.example.unlimintjokes.domain.model.JokeModel
import com.example.unlimintjokes.domain.repository.JokesRepo
import com.example.unlimintjokes.utils.network.DataResult
import kotlinx.coroutines.flow.Flow

class JokesUseCase(private val jokesRepo: JokesRepo) {
    suspend fun getJoke(): Flow<DataResult<JokeModel>> {
        return jokesRepo.getJoke()
    }
}
