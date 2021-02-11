package com.theteampotato.gifit.data.di

import com.theteampotato.gifit.data.remote.Ktor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Singleton
    @Provides
    fun providesHttpClient() : HttpClient = HttpClient(Android) {
        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000

            //proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("localhost", serverPort))
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
    }

    @Singleton
    @Provides
    fun providesKtor(client: HttpClient) : Ktor = Ktor(client)

}