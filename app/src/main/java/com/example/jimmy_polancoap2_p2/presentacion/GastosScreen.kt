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
    var suplidor by remember { mutableStateOf("") }
    var ncf by remember { mutableStateOf("") }
    var itbis by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.cargarGastos()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (suplidor.isNotBlank() && ncf.isNotBlank() && itbis.isNotBlank() && monto.isNotBlank()) {
                        viewModel.agregarGasto(
                            suplidor = suplidor,
                            ncf = ncf,
                            itbis = itbis.toDouble(),
                            monto = monto.toDouble()
                        )
                        suplidor = ""
                        ncf = ""
                        itbis = ""
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
            // Campos para crear un gasto
            OutlinedTextField(
                value = suplidor,
                onValueChange = { suplidor = it },
                label = { Text("Suplidor") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = ncf,
                onValueChange = { ncf = it },
                label = { Text("NCF") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = itbis,
                onValueChange = { itbis = it },
                label = { Text("ITBIS") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
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

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
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
            Text(text = "ID: ${gasto.gastoId ?: "Sin ID"}")
            Text(text = "Suplidor: ${gasto.suplidor ?: "Desconocido"}")
            Text(text = "NCF: ${gasto.ncf ?: "Sin NCF"}")
            Text(text = "ITBIS: ${gasto.itbis ?: 0.0}")
            Text(text = "Monto: ${gasto.monto ?: 0.0}")
            Text(text = "Fecha: ${gasto.fecha ?: "Sin fecha"}")
        }
    }
}

