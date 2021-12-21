package com.naufaldystd.curahanmental.ui.home.tips

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.naufaldystd.curahanmental.databinding.ActivityTipsBinding

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
            tipsAdapter.onItemClick = { selectedItem ->
                val intent = Intent(this@TipsActivity, DetailTipsActivity::class.java)
                intent.putExtra(DetailTipsActivity.TIPS, selectedItem)
                startActivity(intent)
            }
        }
        binding.btnTipsBack.setOnClickListener {
            finish()
        }
    }
}