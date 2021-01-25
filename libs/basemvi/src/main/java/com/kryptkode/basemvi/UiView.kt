package com.kryptkode.basemvi

import android.content.Context
import android.view.View
import androidx.annotation.StringRes

abstract class UiView {

    abstract val rootView: View

    protected val context: Context
        get() = rootView.context

    protected fun getString(@StringRes id: Int, vararg any: Any): String {
        return context.getString(id, *any)
    }
}
