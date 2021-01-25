package com.kryptkode.basemvi

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.ListAdapter
import java.util.concurrent.Executors

@SuppressLint("VisibleForTests")
abstract class ListViewAdapter<S : ComparableById, I : MviIntent>(
    config: AsyncDifferConfig<S> =
        AsyncDifferConfig.Builder(DiffUtilCallback<S>())
            .setBackgroundThreadExecutor(backgroundThreadExecutor)
            .build()
) : ListAdapter<S, UIViewForList<S, I>>(config) {
    abstract fun getComponentForList(i: Int): UIViewForList<S, I>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UIViewForList<S, I> {
        return getComponentForList(viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return (getItem(position) as ComparableById).getType()
    }

    override fun onBindViewHolder(holder: UIViewForList<S, I>, position: Int) {
        holder.render(getItem(position))
    }

    companion object {
        private val backgroundThreadExecutor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
        )
    }
}
