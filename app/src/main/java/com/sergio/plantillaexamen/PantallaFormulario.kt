package com.sergio.plantillaexamen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sergio.plantillaexamen.ui.theme.PlantillaExamenTheme

/** PANTALLA 1: formulario con validación. */
@Composable
fun PantallaFormulario(
    onCalcular: (nombre: String, sexo: String, imc: Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var sexo by rememberSaveable { mutableStateOf("") }
    var peso by rememberSaveable { mutableStateOf("") }
    var estatura by rememberSaveable { mutableStateOf("") }

    // Mensajes de error (null = sin error)
    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorSexo by remember { mutableStateOf<String?>(null) }
    var errorPeso by remember { mutableStateOf<String?>(null) }
    var errorEstatura by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text("Calculadora IMC", style = MaterialTheme.typography.headlineMedium)

        // Nombre
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it; errorNombre = null },
            label = { Text("Nombre") },
            singleLine = true,
            isError = errorNombre != null,
            supportingText = { errorNombre?.let { Text(it) } },
            modifier = Modifier.fillMaxWidth(),
        )

        // Sexo
        Text("Sexo", style = MaterialTheme.typography.titleMedium)
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = sexo == "Hombre", onClick = { sexo = "Hombre"; errorSexo = null })
            Text("Hombre")
            Spacer(Modifier.width(24.dp))
            RadioButton(selected = sexo == "Mujer", onClick = { sexo = "Mujer"; errorSexo = null })
            Text("Mujer")
        }
        errorSexo?.let { Text(it, color = MaterialTheme.colorScheme.error) }

        // Peso
        OutlinedTextField(
            value = peso,
            onValueChange = { peso = it; errorPeso = null },
            label = { Text("Peso (kg)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isError = errorPeso != null,
            supportingText = { Text(errorPeso ?: "Entre 30 y 250 kg") },
            modifier = Modifier.fillMaxWidth(),
        )

        // Estatura
        OutlinedTextField(
            value = estatura,
            onValueChange = { estatura = it; errorEstatura = null },
            label = { Text("Estatura (cm)") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isError = errorEstatura != null,
            supportingText = { Text(errorEstatura ?: "Entre 100 y 230 cm") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.weight(1f))   // empuja el botón abajo

        Button(
            onClick = {
                // Acepta coma o punto decimal
                val p = peso.replace(',', '.').toDoubleOrNull()
                val e = estatura.replace(',', '.').toDoubleOrNull()

                errorNombre = if (nombre.isBlank()) "El nombre es obligatorio" else null
                errorSexo = if (sexo.isEmpty()) "Selecciona un sexo" else null
                errorPeso = when {
                    p == null -> "Introduce un número válido"
                    p !in RANGO_PESO -> "Peso no razonable (30–250 kg)"
                    else -> null
                }
                errorEstatura = when {
                    e == null -> "Introduce un número válido"
                    e !in RANGO_ESTATURA -> "Estatura no razonable (100–230 cm)"
                    else -> null
                }

                // Solo pasa a la pantalla 2 si no hay ningún error
                if (errorNombre == null && errorSexo == null &&
                    errorPeso == null && errorEstatura == null &&
                    p != null && e != null
                ) {
                    onCalcular(nombre.trim(), sexo, calcularImc(p, e))
                }
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Calcular IMC")
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun PantallaFormularioPreview() {
    PlantillaExamenTheme { PantallaFormulario(onCalcular = { _, _, _ -> }) }
}