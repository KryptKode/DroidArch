package com.kryptkode.basemvi

import kotlinx.coroutines.flow.Flow

interface MviViewModel<out S : MviViewState, in I : MviIntent> {
    val viewState: Flow<S>
    suspend fun processIntent(intent: I)
}
