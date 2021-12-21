package com.app.curahanmental.ui.home.tips

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
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
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                tipsContentDetail.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
        }
    }

    companion object{
        const val TIPS = EXTRA_TIPS
    }
}