package com.kryptkode.core.utils.extensions

import androidx.paging.PagingSource

fun <T : Any> List<List<T>>.asPages() = mapIndexed { index, page: List<T> ->
    PagingSource.LoadResult.Page(
        data = page,
        prevKey = if (index > 0) index - 1 else null,
        nextKey = if (index < 9) index else null
    )
}
