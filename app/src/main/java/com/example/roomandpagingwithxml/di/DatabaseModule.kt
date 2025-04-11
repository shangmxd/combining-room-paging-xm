package com.example.roomandpagingwithxml.di

import android.content.Context
import androidx.room.Room
import com.example.roomandpagingwithxml.database.MangaDao
import com.example.roomandpagingwithxml.database.MangaDatabase
import com.example.roomandpagingwithxml.repository.MangaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun getMangaDatabase(@ApplicationContext context: Context): MangaDatabase {
        return Room.databaseBuilder(context, MangaDatabase::class.java, "manga_database").build()
    }

    @Singleton
    @Provides
    fun getMangaDao(mangaDatabase: MangaDatabase):MangaDao {
        return mangaDatabase.getDao()
    }

    @Singleton
    @Provides
    fun getMangaRepository(mangaDao:MangaDao):MangaRepository {
        return MangaRepository(mangaDao)
    }

}