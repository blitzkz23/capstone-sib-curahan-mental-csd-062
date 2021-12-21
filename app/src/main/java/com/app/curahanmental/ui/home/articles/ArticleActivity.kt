package com.app.curahanmental.ui.home.articles

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.curahanmental.data.source.local.entity.ArticlesModel
import com.app.curahanmental.databinding.ActivityArticleBinding
import com.app.curahanmental.utils.Constants.EXTRA_ARTICLE
import com.bumptech.glide.Glide

class ArticleActivity : AppCompatActivity() {
    private val binding: ActivityArticleBinding by lazy { ActivityArticleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val articleContent = intent.getParcelableExtra<ArticlesModel>(ARTICLE)
        setArticleContent(articleContent)
    }

    private fun setArticleContent(articleContent: ArticlesModel?){
        with(binding){
            Glide.with(applicationContext)
                .load(articleContent?.urlToImage)
                .into(articleImageViewDetail)

            articleTitleDetail.text = articleContent?.title
            articleContentDetail.text = articleContent?.content?.substring(0, 200)
            articleAuthorDetail.text = articleContent?.author

            btnContinueRead.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(articleContent?.url)))
            }
            btnArticleBack.setOnClickListener{
                finish()
            }
        }
    }

    companion object{
        const val ARTICLE = EXTRA_ARTICLE
    }
}