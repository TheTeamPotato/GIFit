package com.theteampotato.gifit.home

import kotlinx.coroutines.CoroutineDispatcher

data class DispatcherProvider(
    val io: CoroutineDispatcher,
    val ui: CoroutineDispatcher,
    val default: CoroutineDispatcher
)