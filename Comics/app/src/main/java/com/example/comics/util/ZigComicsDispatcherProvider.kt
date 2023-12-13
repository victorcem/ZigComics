package com.example.comics.util

import kotlinx.coroutines.CoroutineDispatcher

interface ZigComicsDispatcherProvider {

    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}