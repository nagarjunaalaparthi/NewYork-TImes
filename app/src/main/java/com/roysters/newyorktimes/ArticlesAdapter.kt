package com.roysters.newyorktimes

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.roysters.newyorktimes.databinding.NewsItemBinding

class ArticlesAdapter(private val completion: ((ResultsItem) -> Unit)?): RecyclerView.Adapter<ArticleViewHolder>() {

    var articles = ArrayList<ResultsItem>(0)

    public fun addData(list: ArrayList<ResultsItem>) {
        this.articles = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ArticleViewHolder {
        val itemBinding: NewsItemBinding = DataBindingUtil
            .inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.news_item,
                viewGroup,
                false
            )
        return ArticleViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindData(articles[position])
        holder.itemView.setOnClickListener {
            completion?.invoke(articles[holder.adapterPosition])
        }
    }
}

class ArticleViewHolder(private val itemBinding: NewsItemBinding): RecyclerView.ViewHolder(itemBinding.root) {
    fun bindData(article: ResultsItem) {
        itemBinding.article = article

    }
}