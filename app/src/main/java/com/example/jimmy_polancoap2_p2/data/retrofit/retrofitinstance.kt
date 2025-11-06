package com.example.jimmy_polancoap2_p2.data.remote

import com.example.jimmy_polancoap2_p2.data.api.GastosApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://gestionhuacalesapi.azurewebsites.net/api/"

    val api: GastosApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GastosApi::class.java)
    }
}


