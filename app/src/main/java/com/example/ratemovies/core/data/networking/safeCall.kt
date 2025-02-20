package com.example.ratemovies.core.data.networking

import com.example.ratemovies.core.domain.util.DataError
import com.example.ratemovies.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): Result<T, DataError.Remote> {
    val response =
        try {
            execute()
        } catch (e: UnresolvedAddressException) {
            return Result.Error(DataError.Remote.NO_INTERNET)
        } catch (e: SerializationException) {
            return Result.Error(DataError.Remote.SERIALIZATION)
        } catch (e: Exception) {
            coroutineContext.ensureActive()
            return Result.Error(DataError.Remote.UNKNOWN)
        }

    return responseToResult(response)
}