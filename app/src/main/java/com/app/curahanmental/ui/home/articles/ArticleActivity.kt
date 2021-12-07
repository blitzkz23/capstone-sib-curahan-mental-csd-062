package com.app.curahanmental.ui.home.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.curahanmental.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {
    private val binding: ActivityArticleBinding by lazy { ActivityArticleBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}