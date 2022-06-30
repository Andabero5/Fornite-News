package com.example.fornitenews.ui.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.fornitenews.R
import com.example.fornitenews.core.Resource
import com.example.fornitenews.data.model.News
import com.example.fornitenews.data.remote.NewsDataSource
import com.example.fornitenews.databinding.FragmentNewsBinding
import com.example.fornitenews.presentation.NewsViewModel
import com.example.fornitenews.presentation.NewsViewModelFactory
import com.example.fornitenews.repository.NewsRepositoryImpl
import com.example.fornitenews.repository.RetrofitClient
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


class NewsFragment : Fragment(R.layout.fragment_news), NewsAdapter.OnNewsClickListener {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var date:String

    private val viewModel by viewModels<NewsViewModel> { NewsViewModelFactory(NewsRepositoryImpl(
        NewsDataSource(RetrofitClient.webservice)
    )) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        loadInfo()
        binding.btnReload.setOnClickListener { loadInfo() }
    }

    private fun loadInfo(){
        viewModel.getLatestNews().observe(viewLifecycleOwner) { result ->
            when(result){
                is Resource.Loading ->{
                    binding.progressBar.isVisible = true
                }
                is Resource.Failure ->{
                    binding.progressBar.isVisible = false
                    result.exception.localizedMessage?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() }
                }
                is Resource.Success ->{
                    binding.progressBar.isVisible = false
                    var aa = LocalDate.parse("2018-12-31")
                    changeFormatDate(result.data.info.date!!)
                    binding.rvNews.adapter = NewsAdapter(result.data.info.motds,this@NewsFragment)
                    val snapHelper: SnapHelper = LinearSnapHelper()
                    snapHelper.attachToRecyclerView(binding.rvNews)
                }
            }
        }
    }

    private fun changeFormatDate(date: Date){
        SimpleDateFormat("DD-MMM-YYYY").format(date)
        this.date = SimpleDateFormat("dd-MMMM-YYYY").format(date)
    }

    override fun onNewsClick(news: News) {
        val action = NewsFragmentDirections.actionNewsFragmentToDetailFragment(news.image,news.title,date,news.body)
        findNavController().navigate(action)
    }
}