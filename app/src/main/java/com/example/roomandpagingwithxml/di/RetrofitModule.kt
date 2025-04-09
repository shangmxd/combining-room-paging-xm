package com.example.roomandpagingwithxml.di

import com.example.roomandpagingwithxml.model.MangaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {

    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getMangaService(retrofit: Retrofit): MangaService {
        return retrofit.create(MangaService::class.java)
    }
}


