package com.dreamsoftware.saborytv.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

suspend fun <T, R> List<T>.parallelMap(
    context: CoroutineContext = Dispatchers.Default,
    block: suspend (T) -> R
): List<R> =
    map { item ->
        coroutineScope {
            async(context) {
                block(item)
            }
        }
    }.awaitAll()