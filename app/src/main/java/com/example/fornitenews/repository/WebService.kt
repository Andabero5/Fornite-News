package com.example.fornitenews.repository

import com.example.fornitenews.application.AppConstants
import com.example.fornitenews.data.model.NewsList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {
    @GET("news/br")
    suspend fun getLatestNews(): NewsList
}

object RetrofitClient{
    val webservice by lazy{
        Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}