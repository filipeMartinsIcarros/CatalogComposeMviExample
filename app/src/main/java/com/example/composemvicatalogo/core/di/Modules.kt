package com.example.composemvicatalogo.core.di

import com.example.composemvicatalogo.core.connection.ConnectionChecker
import com.example.composemvicatalogo.core.connection.ConnectionCheckerImpl
import com.example.composemvicatalogo.core.retrofit.provideRetrofit
import com.example.composemvicatalogo.core.services.provideServices
import com.example.composemvicatalogo.core.utils.Constants
import org.koin.dsl.module

val serviceModule = module {
    factory<ConnectionChecker> { ConnectionCheckerImpl(get()) }
    single { provideServices(get()) }
    single {
        provideRetrofit(baseUrl = Constants.BASE_URL)
    }
}