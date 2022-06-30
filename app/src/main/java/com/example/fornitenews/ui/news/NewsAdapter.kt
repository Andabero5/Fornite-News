package com.example.fornitenews.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fornitenews.data.model.News
import com.example.fornitenews.databinding.NewsItemBinding

class NewsAdapter(private val newsList:List<News>, private val itemClickListener:OnNewsClickListener):RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    interface OnNewsClickListener{
        fun onNewsClick(news : News)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val itemBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder = NewsHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != (DiffUtil.DiffResult.NO_POSITION)
            }?: return@setOnClickListener
            itemClickListener.onNewsClick(newsList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) =holder.bind(newsList[position])
    override fun getItemCount(): Int = newsList.size

    inner class NewsHolder(private val binding:NewsItemBinding, private val context: Context):RecyclerView.ViewHolder(binding.root){
        fun bind(news: News){
            Glide.with(context).load(news.image).centerCrop().into(binding.imgNews)
            binding.txtTitle.text = news.title
        }
    }
}