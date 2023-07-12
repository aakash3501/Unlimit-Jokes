package com.example.unlimintjokes.di.modules

import com.example.unlimintjokes.utils.Constants.BASE_URL
import com.example.unlimintjokes.utils.network.ApiHelper
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single<Retrofit>() {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val apiModule = module {
    single {
        ApiHelper.getJokeApi(get())
    }
}
