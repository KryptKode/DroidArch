package com.kryptkode.users.utils

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take

fun <T> takeUntilSignal(signal: Flow<Unit>): Flow<T> = flow {
    try {
        signal.take(1).collect {
            currentCoroutineContext().cancelChildren()
        }
    } catch (e: CancellationException) {
        // ignore
        e.printStackTrace()
    }
}
