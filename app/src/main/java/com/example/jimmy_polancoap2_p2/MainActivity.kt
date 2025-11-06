package com.example.jimmy_polancoap2_p2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.jimmy_polancoap2_p2.presentacion.GastosScreen
import com.example.jimmy_polancoap2_p2.ui.theme.Jimmy_polancoAP2_P2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jimmy_polancoAP2_P2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GastosScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

