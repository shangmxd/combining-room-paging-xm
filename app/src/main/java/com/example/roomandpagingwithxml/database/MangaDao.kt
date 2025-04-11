package com.example.roomandpagingwithxml.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.roomandpagingwithxml.database.data.MangaData

@Dao
interface MangaDao {

    suspend fun getAllSavedMangas():LiveData<List<MangaData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertManga(mangaData: MangaData)

    @Delete
    suspend fun deleteMangaFromSaved(manga: MangaData)

}