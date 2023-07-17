package com.example.unlimintjokes.di.modules

import androidx.room.Room
import com.example.unlimintjokes.database.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDataBase::class.java,
            "jokes_db"
        ).build()
    }
}
