package com.example.jimmy_polancoap2_p2.presentacion

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jimmy_polancoap2_p2.domian.model.Gasto

@Composable
fun GastosScreen(
    modifier: Modifier = Modifier,
    viewModel: GastosViewModel = GastosViewModel()
) {
    val gastos by viewModel.gastos.collectAsState()
    var descripcion by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.cargarGastos()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (descripcion.isNotBlank() && monto.isNotBlank()) {
                        viewModel.agregarGasto(descripcion, monto.toDouble())
                        descripcion = ""
                        monto = ""
                    }
                }
            ) {
                Text("+")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = monto,
                onValueChange = { monto = it },
                label = { Text("Monto") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Lista de Gastos",
                style = MaterialTheme.typography.titleLarge
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(gastos) { gasto ->
                    GastoItem(gasto)
                }
            }
        }
    }
}

@Composable
fun GastoItem(gasto: Gasto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = gasto.descripcion, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Monto: ${gasto.monto}")
            Text(text = "Fecha: ${gasto.fecha}")
        }
    }
}
