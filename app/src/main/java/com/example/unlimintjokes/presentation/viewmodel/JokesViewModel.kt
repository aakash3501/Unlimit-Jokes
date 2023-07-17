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
    private var job: Job? = null
    private val fetchDuration = 60000L // 60 seconds

    private var _jokeLiveData = MutableLiveData<JokeModel?>()
    val jokeLiveData: MutableLiveData<JokeModel?> = _jokeLiveData

    private var _jokeListLiveData = MutableLiveData<List<JokeModel>>()
    val jokeListLiveData: LiveData<List<JokeModel>> = _jokeListLiveData

    private var _errorMsgLiveData = MutableLiveData<String>()
    val errorMsgLiveData: LiveData<String> = _errorMsgLiveData

    fun startFetchingJokes() {
        job = viewModelScope.launch {
            while (true) {
                getJokeFromApi()
                delay(fetchDuration)
            }
        }
    }

    private suspend fun getJokeFromApi() {
        jokesUseCase.getJoke().first { result ->
            when (result) {
                is DataResult.Error -> {
                    _errorMsgLiveData.value = result.error
                }
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

    fun getSavedJokes() {
        viewModelScope.launch {
            val jokes = jokesUseCase.getSavedJokes()
            _jokeListLiveData.value = jokes
        }
    }

    fun saveJokesInDB(jokeList: MutableList<JokeModel>) {
        viewModelScope.launch {
            jokesUseCase.deleteAllJokes()
            jokesUseCase.saveJokes(jokeList)
        }
    }
}
