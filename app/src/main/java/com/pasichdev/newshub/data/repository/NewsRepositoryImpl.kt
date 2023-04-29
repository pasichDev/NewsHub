package com.pasichdev.newshub.data.repository

import com.pasichdev.newshub.data.model.NewsResponse
import com.pasichdev.newshub.data.services.ApiService
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
            val responseApi = apiService.getBusinessNews(category = category, country = "en")
            emit(Response.Success(responseApi))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

}
