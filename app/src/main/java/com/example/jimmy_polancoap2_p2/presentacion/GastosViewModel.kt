package com.example.jimmy_polancoap2_p2.presentacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jimmy_polancoap2_p2.domian.model.Gasto
import com.example.jimmy_polancoap2_p2.domian.repository.GastosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GastosViewModel : ViewModel() {
    private val repository = GastosRepository()

    private val _gastos = MutableStateFlow<List<Gasto>>(emptyList())
    val gastos: StateFlow<List<Gasto>> = _gastos

    fun cargarGastos() {
        viewModelScope.launch {
            try {
                val lista = repository.obtenerGastos().map { gasto ->
                    gasto.copy(
                        suplidor = gasto.suplidor?.ifBlank { "Sin suplidor" } ?: "Sin suplidor",
                        ncf = gasto.ncf?.ifBlank { "Sin NCF" } ?: "Sin NCF",
                        fecha = gasto.fecha?.ifBlank { "Sin fecha" } ?: "Sin fecha"
                    )
                }
                _gastos.value = lista
            } catch (e: Exception) {
                println("Error al cargar gastos: ${e.message}")
            }
        }
    }

    fun agregarGasto(suplidor: String, ncf: String, itbis: Double, monto: Double) {
        viewModelScope.launch {
            try {
                val nuevo = Gasto(
                    gastoId = 0,
                    fecha = "2025-11-05T00:00:00",
                    suplidor = suplidor,
                    ncf = ncf,
                    itbis = itbis,
                    monto = monto
                )
                repository.agregarGasto(nuevo)
                cargarGastos()
                println("Gasto agregado correctamente")
            } catch (e: Exception) {
                println("Error al agregar gasto: ${e.message}")
            }
        }
    }
}



