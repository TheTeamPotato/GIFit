package com.theteampotato.gifit.testing

import kotlinx.coroutines.CoroutineDispatcher

data class DispatcherProvider(
    val io: CoroutineDispatcher,
    val ui: CoroutineDispatcher,
    val default: CoroutineDispatcher
)