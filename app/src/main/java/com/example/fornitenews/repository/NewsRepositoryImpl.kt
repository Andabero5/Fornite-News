package com.example.fornitenews.repository

import com.example.fornitenews.data.model.NewsList
import com.example.fornitenews.data.remote.NewsDataSource

class NewsRepositoryImpl(private val dataSource: NewsDataSource):NewsRepository {
    override suspend fun getLatestNews(): NewsList = dataSource.getLatestNews()
}