package com.example.fornitenews.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.fornitenews.core.Resource
import com.example.fornitenews.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class NewsViewModel(private val repo : NewsRepository):ViewModel() {

    fun getLatestNews() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getLatestNews()))
        }catch(e:Exception){
            emit(Resource.Failure(e))
        }
    }
}

class NewsViewModelFactory(private val repo : NewsRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(NewsRepository::class.java).newInstance()
    }

}