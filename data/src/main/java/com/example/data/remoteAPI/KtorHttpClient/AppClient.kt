package com.example.data.remoteAPI.KtorHttpClient

import io.ktor.client.*

interface AppClient {
    val client: HttpClient
}