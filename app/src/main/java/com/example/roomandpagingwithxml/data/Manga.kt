package com.example.roomandpagingwithxml.data

data class Manga(
    val synopsis: String,
    val chapters: Int,
    val images: Image,
    val mal_id: Int,
    val score: Double,
    val title: String
)