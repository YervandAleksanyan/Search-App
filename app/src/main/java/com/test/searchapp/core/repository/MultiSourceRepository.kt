@file:Suppress("unused")

package com.test.searchapp.core.repository

import com.test.searchapp.core.extensions.tryOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.test.searchapp.core.model.Result

interface MultiSourceRepository

fun <T> MultiSourceRepository.cacheElseNetwork(
    networkCall: suspend () -> T,
    cacheCall: suspend () -> T?,
    updateCache: suspend (T) -> Unit
): Flow<Result<T>> = flow {
    // Cache
    val cacheResponse = tryOrNull { cacheCall() }
    if (cacheResponse != null && ((cacheResponse as? List<*>).orEmpty()).isNotEmpty()) {
        emit(Result.Success(cacheResponse))
        return@flow
    }
    // Network
    try {
        val networkResponse = networkCall()
        // Post result
        emit(Result.Success(networkResponse))
        // Update cache
        updateCache(networkResponse)
    } catch (t: Throwable) {
        emit(Result.Error(t))
    }
}
