package com.kryptkode.core.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.kryptkode.core.R

interface ImageLoader {
    fun load(
        imageSource: String,
        target: ImageView,
        @DrawableRes errorResId: Int = R.drawable.ic_photo
    )
}
