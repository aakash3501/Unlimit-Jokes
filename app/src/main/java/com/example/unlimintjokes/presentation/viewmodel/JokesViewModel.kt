package com.example.unlimintjokes.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unlimintjokes.domain.model.JokeModel
import com.example.unlimintjokes.domain.usecase.JokesUseCase
import com.example.unlimintjokes.utils.network.DataResult
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class JokesViewModel(private val jokesUseCase: JokesUseCase) : ViewModel() {
    var job: Job? = null

    private var _jokeLiveData = MutableLiveData<JokeModel?>()
    val jokeLiveData: MutableLiveData<JokeModel?> = _jokeLiveData

    private var _jokeListLiveData = MutableLiveData<JokeModel>()
    val jokeListLiveData: LiveData<JokeModel> = _jokeListLiveData

    fun startFetchingJokes() {
        job = viewModelScope.launch {
            while (true) {
                getJokeFromApi()
                delay(5000L)
            }
        }
    }

    private suspend fun getJokeFromApi() {
        jokesUseCase.getJoke().first { result ->
            when (result) {
                is DataResult.Error -> TODO()
                is DataResult.Success -> {
                    _jokeLiveData.value = result.data
                }
            }
            true
        }
    }

    fun stopFetchingJokes() {
        job?.cancel()
        job = null
    }
}
