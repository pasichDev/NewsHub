package com.pasichdev.newshub.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.network.ApiService
import com.pasichdev.newshub.data.source.PagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : NewsRepository {

    var PAGE_SIZE = 4

    /*
        override fun getBusinessNews(category: String): Flow<Response<NewsResponse>> = flow {
            try {
                emit(Response.Loading)
                val responseApi =
                    apiService.getNewsCategory(category = category, country = "ua", pageSize = 20, page = 1)
                emit(Response.Success(responseApi))
            } catch (e: Exception) {
                emit(Response.Failure(e))
            }
        }.flowOn(Dispatchers.IO)


     */
    override fun getCategoryNews(category: String, country: String): Flow<PagingData<News>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = true
        ),
        pagingSourceFactory = {
            PagingSource(
                response = { pageNext ->
                    apiService.getNewsCategory(
                        category = category,
                        country = country,
                        page = pageNext,
                        pageSize = PAGE_SIZE,
                    )
                }
            )
        }
    ).flow


}
