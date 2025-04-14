package com.example.roomandpagingwithxml.viewmodel

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import coil.ImageLoader
import coil.request.ImageRequest
import com.example.roomandpagingwithxml.data.Manga
import com.example.roomandpagingwithxml.database.data.MangaData
import com.example.roomandpagingwithxml.extensions.toMangaDataClass
import com.example.roomandpagingwithxml.repository.MangaPagingSource
import com.example.roomandpagingwithxml.model.MangaService
import com.example.roomandpagingwithxml.repository.MangaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mangaService: MangaService,
    private val mangaRepository: MangaRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val savedManga = mangaRepository.getSavedManga()

    val mangaResponse = Pager(
        PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {
            MangaPagingSource(mangaService)
        }
    ).liveData.cachedIn(viewModelScope)

    fun deleteMangaFromSaved(mangaData: MangaData){
        viewModelScope.launch(Dispatchers.IO) {
            mangaRepository.deleteSavedManga(mangaData)
        }
    }
    fun saveManga(manga: Manga){
        viewModelScope.launch(Dispatchers.IO) {
            val mangaCover = convertImageURLtoBimap(manga.images.jpg.image_url)
            mangaRepository.addManga(manga.toMangaDataClass(mangaCover!!))
        }
    }

    private suspend fun convertImageURLtoBimap(imageURL: String): Bitmap? {
        val imageRequest = ImageRequest.Builder(context)
            .data(imageURL)
            .build()
        val imageLoader = ImageLoader.Builder(context)
            .build()
        return imageLoader.execute(imageRequest).drawable?.toBitmap()
    }

}