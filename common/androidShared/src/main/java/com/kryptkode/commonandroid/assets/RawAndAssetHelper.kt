package com.kryptkode.commonandroid.assets

import android.content.Context
import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import timber.log.Timber

class RawAndAssetHelper {

    private fun getInputStreams(manager: AssetManager, path: String): InputStream? {
        val file = File(path)
        try {
            return manager.open(file.path)
        } catch (e: IOException) {
            Timber.e(e)
            return null
        }
    }

    private fun getFiles(manager: AssetManager, directory: String?): Array<String>? {
        if (directory == null) {
            return arrayOf()
        }
        try {
            return manager.list(directory)
        } catch (e: IOException) {
            Timber.e(e)
            return arrayOf()
        }
    }

    private fun createFromStream(stream: InputStream?): String? {
        val descBuilder = StringBuilder()
        val reader = BufferedReader(InputStreamReader(stream!!))
        try {
            var line: String? = reader.readLine()
            while (line != null) {
                if (descBuilder.isNotEmpty()) {
                    descBuilder.append("\n")
                }
                descBuilder.append(line)
                line = reader.readLine()
            }
        } catch (e: IOException) {
            Timber.e(e)
            return null
        }

        return descBuilder.toString()
    }

    fun getAssetFileContents(context: Context, path: String): String? {
        return createFromStream(getInputStreams(context.assets, path))
    }

    fun getRawResFileContents(context: Context, fileName: String): String? {
        return try {
            createFromStream(
                context.resources
                    .openRawResource(
                        context.resources.getIdentifier(
                            removeExtension(fileName),
                            "raw",
                            context.packageName
                        )
                    )
            )
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    private fun removeExtension(fileName: String): String {
        return fileName.split(".")[0]
    }
}
