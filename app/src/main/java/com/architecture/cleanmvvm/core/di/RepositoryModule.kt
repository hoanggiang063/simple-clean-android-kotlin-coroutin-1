package com.architecture.cleanmvvm.core.di

import com.architecture.cleanmvvm.core.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val repositoryModule = module {
    single {
        OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build() as OkHttpClient
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(
                get()
            )
            .addConverterFactory(MoshiConverterFactory.create())
            .build() as Retrofit
    }
}