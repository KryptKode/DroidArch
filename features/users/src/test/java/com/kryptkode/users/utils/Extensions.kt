package com.kryptkode.users.utils

import androidx.paging.PagingSource

fun <T : Any> List<T>.asPage() = PagingSource.LoadResult.Page(
    data = this,
    prevKey = 0,
    nextKey = 1
)
