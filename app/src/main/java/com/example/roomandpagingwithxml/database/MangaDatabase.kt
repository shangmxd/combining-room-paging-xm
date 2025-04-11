package com.example.roomandpagingwithxml.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomandpagingwithxml.database.data.MangaData

@Database(entities = [MangaData::class], version = 1)
abstract class MangaDatabase:RoomDatabase() {
    abstract fun getDao(): MangaDao
}