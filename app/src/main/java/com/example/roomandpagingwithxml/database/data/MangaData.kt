package com.example.roomandpagingwithxml.database.data

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MangaData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val mangaTitle:String,
    val mangaDescription:String,
    val mangaChapters:Int,
    val mangaScore:Double,
    val mangaImage: Bitmap,
)