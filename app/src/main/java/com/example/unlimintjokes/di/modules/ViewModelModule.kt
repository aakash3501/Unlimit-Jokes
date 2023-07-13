package com.example.unlimintjokes.di.modules

import com.example.unlimintjokes.presentation.viewmodel.JokesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        JokesViewModel(get())
    }
}
