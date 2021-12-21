package com.naufaldystd.curahanmental.ui.home.tips

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TipsModel(
    var image: Int ?= null,
    var title: String ?= null,
    var description: String ?= null
):Parcelable