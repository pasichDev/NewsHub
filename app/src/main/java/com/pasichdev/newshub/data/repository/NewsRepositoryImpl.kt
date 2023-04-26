package com.pasichdev.newshub.data.repository

import com.pasichdev.newshub.Response
import com.pasichdev.newshub.data.NewsResponse
import com.pasichdev.newshub.data.services.NewsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepositoryImpl constructor(
    private val newsService: NewsService = NewsService.getInstance(),
) : NewsRepository {

    override fun getBusinessNews(category: String): Flow<Response<NewsResponse>> = flow {
        try {
            emit(Response.Loading)
            val responseApi = newsService.getBusinessNews(category = category, country = "en")
            emit(Response.Success(responseApi))
        } catch (e: Exception) {
            emit(Response.Failure(e))
        }
    }.flowOn(Dispatchers.IO)

    /* override fun getDetailGames(id: Int): Flow<Response<News>> = flow{
         try {
             emit(Response.Loading)
             val responseApi = newsService.getGamesDetail(id = id)
             emit(Response.Success(responseApi))
         } catch (e: Exception) {
             emit(Response.Failure(e))
         }
     }.flowOn(Dispatchers.IO)

     */
}
