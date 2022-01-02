package com.hareshnayak.enlight.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hareshnayak.enlight.R
import com.hareshnayak.enlight.databinding.ItemArticlePreviewBinding
import com.hareshnayak.enlight.modals.Article

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    inner class ArticleViewHolder(private val binding: ItemArticlePreviewBinding):RecyclerView.ViewHolder(binding.root)

    private val differCallback = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticlePreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply{
            Glide.with(this).load(article.urlToImage).into(findViewById(R.id.ivArticleImage))
            findViewById<TextView>(R.id.tvSource).text = article.source.name
            findViewById<TextView>(R.id.tvTitle).text = article.title
            findViewById<TextView>(R.id.tvDescription).text = article.description
            findViewById<TextView>(R.id.tvPublishedAt).text = article.publishedAt
            setOnClickListener{
                onItemClickListener?.let{it(article)}
            }
        }
    }
    private var onItemClickListener: ((Article) -> Unit)? = null
    fun setOnTemClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
