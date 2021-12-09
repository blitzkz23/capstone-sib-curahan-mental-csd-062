package com.app.curahanmental.ui.home.tips

import com.app.curahanmental.R

object TipsData {
    private var data = arrayOf(
        TipsModel(
            R.drawable.tips_image1,
            "Jaga pola makan teratur",
            "ohe"
        ),
        TipsModel(
            R.drawable.tips_image4,
            "Olahraga secara konsisten",
            "ohe"
        ),
        TipsModel(
            R.drawable.tips_image2,
            "Coba aktivitas kesukaan anda",
            "ohe"
        ),
        TipsModel(
            R.drawable.tips_image3,
            "Tetap terhubung dengan orang lain",
            "ohe"
        )
    )

    val tipsList: ArrayList<TipsModel> get() {
        val list = arrayListOf<TipsModel>()
        for(arr in data){
            val tips = TipsModel()
            tips.image = arr.image
            tips.title = arr.title
            tips.description = arr.description
            list.add(tips)
        }
        return list
    }
}