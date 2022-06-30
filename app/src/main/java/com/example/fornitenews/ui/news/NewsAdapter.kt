package com.example.fornitenews.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fornitenews.data.model.News
import com.example.fornitenews.databinding.NewsItemBinding
import java.util.*
import kotlin.collections.ArrayList

class NewsAdapter(
    private val newsList: List<News>,
    private val itemClickListener: OnNewsClickListener
) : RecyclerView.Adapter<NewsAdapter.NewsHolder>(), Filterable {

    var newsFilterList: List<News> = newsList

    interface OnNewsClickListener {
        fun onNewsClick(news: News)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val itemBinding =
            NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = NewsHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf {
                it != (DiffUtil.DiffResult.NO_POSITION)
            } ?: return@setOnClickListener
            itemClickListener.onNewsClick(newsFilterList[position])
        }

        return holder
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(newsFilterList[position])
    }

    override fun getItemCount(): Int = newsFilterList.size

    inner class NewsHolder(private val binding: NewsItemBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            Glide.with(context).load(news.image).centerCrop().into(binding.imgNews)
            binding.txtTitle.text = news.title
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString()?.lowercase() ?: ""
                newsFilterList = if (charString.isEmpty()) newsList else {
                    val filteredList = mutableListOf<News>()
                    newsList
                        .filter {
                            (it.title.lowercase().contains(constraint!!))
                        }
                        .forEach { filteredList.add(it) }
                    filteredList
                }
                return FilterResults().apply { values = newsFilterList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                newsFilterList = if (results?.values == null)
                    listOf()
                else
                    results.values as List<News>
                notifyDataSetChanged()
            }
        }
    }
}