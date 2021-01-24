package com.kryptkode.basemvi

import kotlinx.coroutines.flow.Flow

interface MviView<out I : MviIntent, in S : MviViewState> {
    fun render(state: S)
    val intents: Flow<I>
}
