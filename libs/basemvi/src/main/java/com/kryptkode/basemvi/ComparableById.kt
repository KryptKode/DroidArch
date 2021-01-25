package com.kryptkode.basemvi

interface ComparableById : MviViewState {
    val idForComparison: String
    fun getType(): Int {
        return 0
    }
}
