package com.example.fornitenews.data.model

import java.util.*

data class Data(val date: Date?,val motds:List<NewsList> = listOf())
data class NewsList(val title:String = "", val body:String = "", val image:String = "")
