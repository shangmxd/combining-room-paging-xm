package com.example.roomandpagingwithxml.extensions

import android.graphics.Bitmap
import com.example.roomandpagingwithxml.data.Manga
import com.example.roomandpagingwithxml.database.data.MangaData

fun Manga.toMangaDataClass(mangaCover: Bitmap):MangaData {
    return MangaData(id = this.mal_id,
        mangaTitle = this.title,
        mangaDescription = this.synopsis,
        mangaChapters = this.chapters,
        mangaScore = this.score,
        mangaImage = mangaCover)
}