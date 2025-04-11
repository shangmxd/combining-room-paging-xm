package com.example.roomandpagingwithxml.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.roomandpagingwithxml.data.Manga
import com.example.roomandpagingwithxml.model.MangaService
import java.lang.Exception

class MangaPagingSource(private val mangaService: MangaService) : PagingSource<Int, Manga>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Manga> {
        try {
            val currentPagePosition = params.key ?: 1
            val result = mangaService.getAllMangas(currentPagePosition)
            return LoadResult.Page(
                data = result.data,
                prevKey = if (currentPagePosition == 1) null else currentPagePosition.minus(1),
                nextKey = if (currentPagePosition == result.data.size) null else currentPagePosition.plus(
                    1
                )
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Manga>): Int? {
        val anchorPosition = state.anchorPosition
        if (anchorPosition != null) {
            val currentPosition = state.closestPageToPosition(anchorPosition)
            if (currentPosition != null) {
                return currentPosition.prevKey?.plus(1)?: currentPosition.nextKey?.minus(1)
            }
        }
        return null
    }
}