package com.example.roomandpagingwithxml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.example.roomandpagingwithxml.model.MangaPagingSource
import com.example.roomandpagingwithxml.model.MangaService
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mangaService: MangaService):ViewModel() {

    val mangaResponse = Pager(
        PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {
            MangaPagingSource(mangaService)
        }
    ).liveData.cachedIn(viewModelScope)

}