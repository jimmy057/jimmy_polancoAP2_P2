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
                        descripcion = gasto.descripcion.ifBlank { "Sin descripción" },
                        fecha = gasto.fecha.ifBlank { "Sin fecha" }
                    )
                }
                _gastos.value = lista
            } catch (e: Exception) {
                println("Error al cargar gastos: ${e.message}")
            }
        }
    }


    fun agregarGasto(descripcion: String, monto: Double) {
        viewModelScope.launch {
            try {
                val nuevo = Gasto(
                    descripcion = descripcion,
                    monto = monto,
                    fecha = "2025-11-05",
                )
                repository.agregarGasto(nuevo)
                cargarGastos()
            } catch (e: Exception) {
                println("Error al agregar gasto: ${e.message}")
            }
        }
    }
}

