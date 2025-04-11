package com.example.roomandpagingwithxml.repository

import androidx.lifecycle.LiveData
import com.example.roomandpagingwithxml.database.MangaDao
import com.example.roomandpagingwithxml.database.data.MangaData
import javax.inject.Inject

class MangaRepository @Inject constructor(private val mangaDao: MangaDao) {

    suspend fun getSavedManga():LiveData<List<MangaData>> {
        return mangaDao.getAllSavedMangas()
    }

    suspend fun deleteSavedManga(savedManga:MangaData) {
        mangaDao.deleteMangaFromSaved(savedManga)
    }

    suspend fun addManga(mangaData:MangaData) {
        mangaDao.insertManga(mangaData)
    }
}