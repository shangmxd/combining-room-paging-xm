package com.example.roomandpagingwithxml.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomandpagingwithxml.database.data.MangaData

@Dao
interface MangaDao {

    @Query("SELECT *FROM MangaData")
    fun getAllSavedMangas():LiveData<List<MangaData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertManga(mangaData: MangaData)

    @Delete
    fun deleteMangaFromSaved(manga: MangaData)

}