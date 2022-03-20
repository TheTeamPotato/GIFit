package com.theteampotato.gifit.data.di

import com.theteampotato.gifit.data.remote.Ktor

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

@Module
@InstallIn(SingletonComponent::class)
object KtorModule {

    @Singleton
    @Provides
    fun providesHttpClient(): HttpClient = HttpClient(Android) {
        engine {
            connectTimeout = 100_000
            socketTimeout = 100_000
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    @Singleton
    @Provides
    fun providesKtor(client: HttpClient) : Ktor = Ktor(client)

}