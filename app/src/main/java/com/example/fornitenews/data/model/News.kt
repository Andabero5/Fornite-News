package com.example.fornitenews.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class NewsList(@SerializedName("data") val info:Data)
data class Data(val date: Date?,val motds:List<News> = listOf())
data class News(val title:String = "", val body:String = "", val image:String = "")
