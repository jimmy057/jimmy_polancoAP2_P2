package com.example.jimmy_polancoap2_p2.domian.repository

import com.example.jimmy_polancoap2_p2.data.remote.RetrofitInstance
import com.example.jimmy_polancoap2_p2.domian.model.Gasto

class GastosRepository {

    private val api = RetrofitInstance.api

    suspend fun obtenerGastos(): List<Gasto> {
        return try {
            api.obtenerGastos()
        } catch (e: Exception) {
            println("Error al obtener gastos: ${e.message}")
            emptyList()
        }
    }
    suspend fun agregarGasto(gasto: Gasto) {
        try {
            api.agregarGasto(gasto)
        } catch (e: Exception) {
            println("Error al agregar gasto: ${e.message}")
        }
    }
}

