package com.app.curahanmental.ui.home.tips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.curahanmental.databinding.ActivityDetailTipsBinding
import com.app.curahanmental.utils.Constants.EXTRA_TIPS
import com.bumptech.glide.Glide

class DetailTipsActivity : AppCompatActivity() {
    private val binding: ActivityDetailTipsBinding by lazy { ActivityDetailTipsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val tipsContent = intent.getParcelableExtra<TipsModel>(TIPS)
        setTipsContent(tipsContent)
    }

    private fun setTipsContent(tipsContent: TipsModel?){
        with(binding){
            Glide.with(applicationContext)
                .load(tipsContent?.image)
                .into(tipsImageViewDetail)

            tipsTitleDetail.text = tipsContent?.title
            tipsContentDetail.text = tipsContent?.description

            btnTipsDetailBack.setOnClickListener {
                finish()
            }
        }
    }

    companion object{
        const val TIPS = EXTRA_TIPS
    }
}