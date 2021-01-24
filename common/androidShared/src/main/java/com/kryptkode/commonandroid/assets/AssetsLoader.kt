package com.kryptkode.commonandroid.assets

import android.content.Context

class AssetsLoader(val context: Context) {

    private val assetHelper = RawAndAssetHelper()

    fun getRawResourceDataAsString(fileName: String): String? {
        return assetHelper.getRawResFileContents(
            context,
            fileName
        )
    }

    fun getAssetDataAsString(fileName: String): String? {
        return assetHelper.getAssetFileContents(
            context,
            fileName
        )
    }
}
