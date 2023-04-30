package com.pasichdev.newshub.data.repository

import androidx.paging.PagingData
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.model.NewsResponse
import com.pasichdev.newshub.data.network.ApiService
import com.pasichdev.newshub.utils.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : NewsRepository {

    override fun getBusinessNews(category: String): Flow<Response<NewsResponse>> = flow {
        try {
            emit(Response.Loading)
            val responseApi =
                apiService.getNewsCategory(category = category, country = "ua", size = 5)
            emit(Response.Success(responseApi))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getCategoryNews(): Flow<PagingData<News>> {
        TODO("Not yet implemented")
    }

}
