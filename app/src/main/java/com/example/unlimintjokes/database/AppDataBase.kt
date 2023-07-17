package com.example.unlimintjokes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.unlimintjokes.domain.model.JokeModel

@Database(entities = [JokeModel::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun jokesDao(): JokesDao
}
