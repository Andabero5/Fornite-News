package com.example.fornitenews.repository


import com.example.fornitenews.data.model.NewsList
import retrofit2.http.GET

interface NewsRepository {
    suspend fun getLatestNews():NewsList
}