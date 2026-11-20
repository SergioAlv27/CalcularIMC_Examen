package com.sergio.plantillaexamen

// Rangos razonables para la validación
val RANGO_PESO = 30.0..250.0        // kg  (rechaza 500 kg o 22 kg)
val RANGO_ESTATURA = 100.0..230.0   // cm

// Listas INMUTABLES de ilustraciones: 0 = flaco, 1 = normal, 2 = gordo
val ILUSTRACIONES_HOMBRE: List<Int> = listOf(
    R.drawable.hombre_delgado,
    R.drawable.hombre_normal,
    R.drawable.hombre_gordo,
)
val ILUSTRACIONES_MUJER: List<Int> = listOf(
    R.drawable.mujer_flaca,
    R.drawable.mujer_normal,
    R.drawable.mujer_gorda,
)

/** Fórmula: peso (kg) / estatura (m)² */
fun calcularImc(pesoKg: Double, estaturaCm: Double): Double {
    val metros = estaturaCm / 100
    return pesoKg / (metros * metros)
}

/** Valor cualitativo según el IMC */
fun valorCualitativo(imc: Double): String = when {
    imc < 18.5 -> "Bajo peso"
    imc < 25.0 -> "Peso normal"
    imc < 30.0 -> "Sobrepeso"
    else -> "Obesidad"
}

/** Elige la ilustración según el IMC y el sexo */
fun ilustracion(imc: Double, sexo: String): Int {
    val lista = if (sexo == "Hombre") ILUSTRACIONES_HOMBRE else ILUSTRACIONES_MUJER
    val indice = when {
        imc < 18.5 -> 0   // flaco/a
        imc < 25.0 -> 1   // normal
        else -> 2         // gordo/a
    }
    return lista[indice]
}