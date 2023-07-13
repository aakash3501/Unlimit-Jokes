package com.example.unlimintjokes.di.modules

import com.example.unlimintjokes.domain.usecase.JokesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        JokesUseCase(get())
    }
}
