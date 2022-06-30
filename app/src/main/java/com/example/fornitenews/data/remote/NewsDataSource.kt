package com.example.fornitenews.data.remote

import com.example.fornitenews.data.model.NewsList
import com.example.fornitenews.repository.WebService

class NewsDataSource(private val webService: WebService) {
    suspend fun getLatestNews():NewsList = webService.getLatestNews()
}