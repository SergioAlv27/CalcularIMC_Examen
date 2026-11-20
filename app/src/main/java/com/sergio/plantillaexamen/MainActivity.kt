package com.sergio.plantillaexamen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sergio.plantillaexamen.ui.theme.PlantillaExamenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlantillaExamenTheme {
                App()
            }
        }
    }
}

/**
 * Navegación con un único estado:
 *   enSegunda == false -> Pantalla 1 (formulario)
 *   enSegunda == true  -> Pantalla 2 (resultado)
 */
@Composable
fun App() {
    var enSegunda by rememberSaveable { mutableStateOf(false) }
    var nombre by rememberSaveable { mutableStateOf("") }
    var sexo by rememberSaveable { mutableStateOf("") }
    var imc by rememberSaveable { mutableStateOf(0.0) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        if (enSegunda) {
            PantallaResultado(
                nombre = nombre,
                sexo = sexo,
                imc = imc,
                onVolver = { enSegunda = false },
                modifier = modifier,
            )
        } else {
            PantallaFormulario(
                onCalcular = { n, s, resultado ->
                    nombre = n
                    sexo = s
                    imc = resultado
                    enSegunda = true
                },
                modifier = modifier,
            )
        }
    }
}