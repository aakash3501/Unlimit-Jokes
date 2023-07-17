package com.example.unlimintjokes.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.unlimintjokes.domain.model.JokeModel

@Dao
interface JokesDao {

    @Insert
    suspend fun insertJokes(jokes: List<JokeModel>)

    @Query("SELECT * FROM jokesData")
    suspend fun getAllJokes(): List<JokeModel>

    @Query("DELETE FROM jokesData")
    suspend fun deleteAllJokes()
}
