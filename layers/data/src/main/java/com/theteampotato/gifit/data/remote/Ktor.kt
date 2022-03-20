package com.theteampotato.gifit.data.remote

import io.ktor.client.*
import io.ktor.client.request.*

import javax.inject.Inject

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Ktor @Inject constructor(val client: HttpClient) {

    suspend inline fun <reified ResponseType> executeGetRequest(urlString: String) =
        withContext(Dispatchers.IO) {
            return@withContext client.get(urlString) as ResponseType
        }

    fun close() = client.close()

}