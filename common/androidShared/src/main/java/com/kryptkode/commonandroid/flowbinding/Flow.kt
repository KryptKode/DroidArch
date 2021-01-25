package com.kryptkode.commonandroid.flowbinding

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow

fun <T> MutableStateFlow<T>.asFlow(): Flow<T> = this
fun <T> MutableSharedFlow<T>.asFlow(): Flow<T> = this
