package com.app.curahanmental.ui.home.tips

import android.graphics.drawable.Drawable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TipsModel(
    var image: Int ?= null,
    var title: String ?= null,
    var description: String ?= null
):Parcelable