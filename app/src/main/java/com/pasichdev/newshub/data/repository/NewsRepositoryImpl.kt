package com.pasichdev.newshub.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pasichdev.newshub.data.LocalDatabase
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.network.ApiService
import com.pasichdev.newshub.data.source.PagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localDatabase: LocalDatabase,
) : NewsRepository {

    private var PAGE_SIZE_NEWS = 4
    override fun savedNews(news: News) {
        // localDatabase.savedDao.savedNews(news)
    }

    override fun getCategoryNews(category: String, country: String): Flow<PagingData<News>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE_NEWS,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            PagingSource(
                response = { pageNext ->
                    apiService.getHeadlinesCategory(
                        category = category,
                        country = country,
                        page = pageNext,
                        pageSize = PAGE_SIZE_NEWS,
                    )
                }
            )
        }
    ).flow


}
