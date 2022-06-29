package com.example.fornitenews.ui.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fornitenews.R
import com.example.fornitenews.databinding.FragmentNewsBinding

class NewsFragment : Fragment(R.layout.fragment_news) {
    private lateinit var binding: FragmentNewsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
    }
}