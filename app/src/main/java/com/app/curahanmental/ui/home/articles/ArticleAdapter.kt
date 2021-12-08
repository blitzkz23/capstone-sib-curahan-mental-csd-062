package com.app.curahanmental.ui.home.articles

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.curahanmental.data.source.remote.entity.ArticleEntity
import com.app.curahanmental.databinding.ItemArticleHomeBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ArticleAdapter: RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private var listArticle = ArrayList<ArticleEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleAdapter.ArticleViewHolder {
        val binding = ItemArticleHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ArticleViewHolder, position: Int) {
        val article = listArticle[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = listArticle.size

    inner class ArticleViewHolder(private var binding: ItemArticleHomeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(content: ArticleEntity){
            binding.apply {
                Glide.with(itemView.context)
                    .load(content.urlToImage)
                    .into(articleImageViewHome)
                articleTitleHome.text = content.title
                articleDescriptionHome.text = content.description

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ArticleActivity::class.java)
                    intent.putExtra(ArticleActivity.ARTICLE, content)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setArticleData(newArticle: List<ArticleEntity>?){
        if (newArticle == null) return
        listArticle.clear()
        listArticle.addAll(newArticle)
        notifyDataSetChanged()
    }
}