package com.example.jimmy_polancoap2_p2.data.api

import com.example.jimmy_polancoap2_p2.domian.model.Gasto
import retrofit2.http.*

interface GastosApi {

    @GET("Gastos")
    suspend fun obtenerGastos(): List<Gasto>

    @POST("Gastos")
    suspend fun agregarGasto(@Body gasto: Gasto)
}

