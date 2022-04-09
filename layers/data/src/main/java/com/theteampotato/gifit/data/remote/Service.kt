package com.theteampotato.gifit.data.remote

import javax.inject.Inject

open class Service(val baseUrl: String) {

    @Inject
    lateinit var ktor: Ktor

    suspend inline fun <reified ResponseType> String.execute() =
        ktor.executeGetRequest(urlString = baseUrl + this) as ResponseType

    fun close() = ktor.close()

}