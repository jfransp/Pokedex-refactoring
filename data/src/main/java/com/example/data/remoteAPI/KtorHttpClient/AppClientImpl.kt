package com.example.data.remoteAPI.KtorHttpClient

import com.example.util.Constants.Companion.CONNECT_TIMEOUT_MILLIS
import com.example.util.Constants.Companion.REQUEST_TIMEOUT_MILLIS
import com.example.util.Constants.Companion.SOCKET_TIMEOUT_MILLIS
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*

//I tried instantiating this on the DI injecting Gson as a dependency, but couldn't figure out
//how for some reason - but I don't think it matters because if this class is going to be a singleton
//the GsonSerializer is only going to be instantiated once too.
class AppClientImpl: AppClient {
    override val client: HttpClient =
        HttpClient(OkHttp) {
            install(JsonFeature) {
                serializer = GsonSerializer()
            }
            install(HttpTimeout) {
                connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
                socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
                requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            }
        }
}
