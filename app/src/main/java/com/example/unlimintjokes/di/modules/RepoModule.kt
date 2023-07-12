package com.example.unlimintjokes.di.modules

import com.example.unlimintjokes.data.repoImplementation.JokesRepoImpl
import com.example.unlimintjokes.domain.repository.JokesRepo
import org.koin.dsl.module

val apiRepoModule = module {
    single<JokesRepo> {
        JokesRepoImpl()
    }
}
