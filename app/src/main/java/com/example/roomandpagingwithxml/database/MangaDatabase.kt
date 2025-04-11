package com.example.roomandpagingwithxml.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomandpagingwithxml.database.converters.Converters
import com.example.roomandpagingwithxml.database.data.MangaData

@Database(entities = [MangaData::class], version = 1)
@TypeConverters(Converters::class)
abstract class MangaDatabase:RoomDatabase() {
    abstract fun getDao(): MangaDao
}