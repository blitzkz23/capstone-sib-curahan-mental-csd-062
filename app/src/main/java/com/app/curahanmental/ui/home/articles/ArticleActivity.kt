package com.app.curahanmental.ui.home.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.curahanmental.data.source.remote.entity.ArticleEntity
import com.app.curahanmental.databinding.ActivityArticleBinding
import com.app.curahanmental.utils.Constants.EXTRA_ARTICLE
import com.bumptech.glide.Glide

class ArticleActivity : AppCompatActivity() {
    private val binding: ActivityArticleBinding by lazy { ActivityArticleBinding.inflate(layoutInflater) }
    private lateinit var articleContent: ArticleEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        articleContent = intent.getSerializableExtra(ARTICLE) as ArticleEntity
        setArticleContent()
    }

    private fun setArticleContent(){
        with(binding){
            Glide.with(applicationContext)
                .load(articleContent.urlToImage)
                .into(articleImageViewDetail)

            articleTitleDetail.text = articleContent.title
            articleContentDetail.text = articleContent.content
            articleAuthorDetail.text = articleContent.author

            btnSettingsBack.setOnClickListener{
                finish()
            }
        }
    }

    companion object{
        const val ARTICLE = EXTRA_ARTICLE
    }
}