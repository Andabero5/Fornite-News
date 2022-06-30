package com.example.fornitenews.data.remote

import com.example.fornitenews.data.model.NewsList

class NewsDataSource {
    fun getLatestNews():NewsList{
        return NewsList()
    }
}