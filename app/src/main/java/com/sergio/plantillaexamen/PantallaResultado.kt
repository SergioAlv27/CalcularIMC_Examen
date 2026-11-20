package com.sergio.plantillaexamen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sergio.plantillaexamen.ui.theme.PlantillaExamenTheme

/** PANTALLA 2: nombre, IMC, ilustración, valor cualitativo y botón de regreso. */
@Composable
fun PantallaResultado(
    nombre: String,
    sexo: String,
    imc: Double,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(nombre, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("IMC: %.1f".format(imc), style = MaterialTheme.typography.titleLarge)

        Card(modifier = Modifier.weight(1f).fillMaxWidth()) {
            Image(
                painter = painterResource(ilustracion(imc, sexo)),
                contentDescription = "Ilustración $sexo - ${valorCualitativo(imc)}",
                modifier = Modifier.fillMaxSize().padding(16.dp),
            )
        }

        Text(
            valorCualitativo(imc),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )

        OutlinedButton(onClick = onVolver, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 780)
@Composable
private fun PantallaResultadoPreview() {
    PlantillaExamenTheme { PantallaResultado("Sergio", "Hombre", 23.4, onVolver = {}) }
}