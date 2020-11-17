package com.example.firebasebackend.di

import com.example.firebasebackend.data.api.FirebaseApi
import com.example.firebasebackend.data.api.FirebaseApiImpl
import com.example.firebasebackend.data.repository.FirebaseRepository
import com.example.firebasebackend.data.repository.FirebaseRepositoryImpl
import com.example.firebasebackend.ui.main.MainViewModel
import com.example.firebasebackend.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel() }
}

val repositoryModule: Module = module {
    single<FirebaseRepository> { FirebaseRepositoryImpl(get()) }
}

val apiModule: Module = module {
    single<FirebaseApi> { FirebaseApiImpl() }
}

val appModules = listOf(viewModelModule, repositoryModule, apiModule)
