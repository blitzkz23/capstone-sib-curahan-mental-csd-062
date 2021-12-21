package com.naufaldystd.curahanmental.ui.home.articles

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufaldystd.curahanmental.data.source.local.entity.ArticlesModel
import com.naufaldystd.curahanmental.databinding.ItemArticleHomeBinding
import com.bumptech.glide.Glide

class ArticleAdapter: RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    private var listArticle = ArrayList<ArticlesModel>()
    var onItemClick: ((ArticlesModel) -> Unit)? = null

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
        fun bind(content: ArticlesModel){
            binding.apply {
                Glide.with(itemView.context)
                    .load(content.urlToImage)
                    .into(articleImageViewHome)
                articleTitleHome.text = content.title
                articleDescriptionHome.text = content.description
            }
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listArticle[absoluteAdapterPosition])
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setArticleData(newArticle: List<ArticlesModel>?){
        if (newArticle == null) return
        listArticle.clear()
        listArticle.addAll(newArticle)
        notifyDataSetChanged()
    }
}