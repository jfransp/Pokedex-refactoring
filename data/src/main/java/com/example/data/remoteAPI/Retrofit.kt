package com.example.data.remoteAPI

import com.example.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit() {
    operator fun invoke(): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
