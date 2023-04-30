package com.pasichdev.newshub.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.model.NewsResponse


class PagingSource(
    private val response: suspend (Int) -> NewsResponse,
) : PagingSource<Int, News>() {

    override fun getRefreshKey(state: PagingState<Int, News>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {
        return try {
            val nextPage = params.key ?: 1
            val gamesList = response.invoke(nextPage)
            LoadResult.Page(
                data = gamesList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = nextPage + 1

            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}