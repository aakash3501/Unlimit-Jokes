package com.example.unlimintjokes.app

import android.app.Application
import com.example.unlimintjokes.di.modules.apiModule
import com.example.unlimintjokes.di.modules.apiRepoModule
import com.example.unlimintjokes.di.modules.dataBaseModule
import com.example.unlimintjokes.di.modules.networkModule
import com.example.unlimintjokes.di.modules.useCaseModule
import com.example.unlimintjokes.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class JokesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@JokesApplication)

            modules(
                networkModule,
                apiModule,
                apiRepoModule,
                viewModelModule,
                useCaseModule,
                dataBaseModule
            )
        }
    }
}
