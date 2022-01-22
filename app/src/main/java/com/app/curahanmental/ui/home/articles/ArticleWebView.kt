package com.app.curahanmental.ui.home.articles

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.app.curahanmental.R
import com.app.curahanmental.utils.Constants.EXTRA_URL
import com.google.android.material.button.MaterialButton

class ArticleWebView : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_article_web_view)

		val url = intent.getStringExtra(EXTRA_URL)

		val articleWebView = findViewById<WebView>(R.id.article_webview)
		if (url != null) {
			articleWebView.loadUrl(url)
		}

		val backButton = findViewById<MaterialButton>(R.id.home_btn_settings)
		backButton.setOnClickListener {
			super.onBackPressed()
		}
	}

	companion object {
		const val URL = EXTRA_URL
	}
}