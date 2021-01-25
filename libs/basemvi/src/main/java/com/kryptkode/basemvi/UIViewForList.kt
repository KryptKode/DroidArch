package com.kryptkode.basemvi

import android.content.Context
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

abstract class UIViewForList<in S : MviViewState, I : MviIntent>(itemView: ViewGroup) :
    RecyclerView.ViewHolder(
        itemView
    ),
    MviView<I, S> {

    protected val context: Context
        get() = itemView.context

    protected fun getString(@StringRes id: Int, vararg any: Any): String {
        return context.getString(id, *any)
    }
}
