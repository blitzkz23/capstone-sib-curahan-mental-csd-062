package com.naufaldystd.curahanmental.ui.home.tips

import com.naufaldystd.curahanmental.R

object TipsData {
    private var data = arrayOf(
        TipsModel(
            R.drawable.tips_image1,
            "Jaga pola makan teratur",
            "Penelitian tentang hubungan antara diet dan kesehatan mental telah membuktikan bahwa pola makan dan juga nutrisi yang baik dapat berpengaruh pada kesehatan mental jangka pendek ataupun jangka panjang. " +
                    "Sarapan teratur menjadi salah satu hal yang penting dan berpengaruh pada kesehatan mental, karena ketika kita melewatkan sarapan akan menyebabkan kelelahan dan menimbulkan perasaan linglung. " +
                    "Minum air yang cukup dan hindari minum minuman berperisa ataupun minuman beralkohol, tidak kalah penting juga penuhi nutrisi harian dengan vitamin D yang umumnya dapat kita peroleh dengan sinar matahari atau suplemen."
        ),
        TipsModel(
            R.drawable.tips_image4,
            "Olahraga secara konsisten",
            "Tidak hanya berpengaruh pada kesehatan fisik, olahraga juga dapat berpengaruh pada kesehatan mental seseorang, meskipun begitu kita juga harus tetap menyeimbangkan rutinitas lainnya seperti istirahat yang cukup. " +
                    "Olahraga jika dilakukan secara teratur, akan mampu meningkatkan suasana hati karena peningkatan kadar hormon endorfin. " +
                    "Selain itu olahraga juga dapat membantu meningkatkan kualitas tidur dan meningkatkan fokus serta konsentrasi."
        ),
        TipsModel(
            R.drawable.tips_image2,
            "Bersikap observan pada diri",
            "Temukan alasan dibalik mengapa kita mengalami suatu masalah, dimulai dari mengetahui tanda-tanda bahwa diri kita sedang mengalami suatu kondisi akibat pengaruh dari stress. " +
                    "Seperti sulit tidur, merasa mudah marah dan depresi serta kekurangan energi. Berangkat dari hal tersebut kita identifikasi permasalahan atau akar yang menyebabkan kita mengalami stress dan mencoba untuk mencari soolusinya. " +
                    "Salah satu solusi mungkin bisa dimulai dari menerapkan tips - tips sebelumnya hingga mencatat kejadian serta solusi dari permasalahan tersebut. "
        ),
        TipsModel(
            R.drawable.tips_image3,
            "Tetap terhubung dengan orang lain",
            "Anda tidak sendirian, maka dari itu tetaplah berhubungan atau berkomunikasi dengan orang-orang disekitar anda yang sekiranya dapat membantu anda dalam memberikan dukungan emosional ataupun membantu secara langsung. " +
                    "Jika dibutuhkan, untuk mengurangi stres berceritalah kepada teman dekat anda ataupun keluarga mengenai permasalahan anda dan coba diskusikan bagaimana cara mengatasi permasalahan tersebut. "
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