package com.example.jimmy_polancoap2_p2.domian.model

data class Gasto(
    val gastoId: Int? = null,
    val fecha: String? = "",
    val suplidor: String? = "",
    val ncf: String? = "",
    val itbis: Double? = 0.0,
    val monto: Double? = 0.0
)
