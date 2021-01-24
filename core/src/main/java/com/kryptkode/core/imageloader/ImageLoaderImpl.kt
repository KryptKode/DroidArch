package com.kryptkode.core.imageloader

import android.widget.ImageView
import com.kryptkode.commonandroid.customviews.PlaceHolderDrawable
import com.kryptkode.commonandroid.imageloader.GlideApp
import javax.inject.Inject

class ImageLoaderImpl @Inject constructor() : ImageLoader {

    override fun load(
        imageSource: String,
        target: ImageView,
        errorResId: Int
    ) {
        GlideApp.with(target)
            .load(imageSource)
            .placeholder(PlaceHolderDrawable(target.context))
            .error(errorResId)
            .into(target)
    }
}
