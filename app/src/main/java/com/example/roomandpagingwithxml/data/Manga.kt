package com.example.roomandpagingwithxml.data

data class Manga(
    val background: String,
    val chapters: Int,
    val images: Image,
    val mal_id: Int,
    val rank: Int,
    val score: Double,
    val title: String,
    val volumes: Int
)