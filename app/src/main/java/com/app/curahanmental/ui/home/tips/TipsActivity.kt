package com.app.curahanmental.ui.home.tips

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.curahanmental.databinding.ActivityTipsBinding

class TipsActivity : AppCompatActivity() {
    private val binding: ActivityTipsBinding by lazy { ActivityTipsBinding.inflate(layoutInflater) }
    private var listTips: ArrayList<TipsModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        listTips.addAll(TipsData.tipsList)
        showRecyclerView()
    }

    private fun showRecyclerView(){
        with(binding){
            rvTips.layoutManager = LinearLayoutManager(applicationContext)
            val tipsAdapter = TipsAdapter(listTips)
            rvTips.adapter = tipsAdapter
        }
        binding.btnSettingsBack.setOnClickListener {
            finish()
        }
    }
}