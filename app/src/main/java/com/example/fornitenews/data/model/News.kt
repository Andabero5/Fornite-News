package com.example.fornitenews.data.model

import java.util.*

data class NewsList(val data:Data)
data class Data(val date: Date?,val motds:List<News> = listOf())
data class News(val title:String = "", val body:String = "", val image:String = "")
