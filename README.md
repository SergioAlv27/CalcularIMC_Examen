# Calculadora IMC

App Android (Kotlin + Jetpack Compose + Material 3) que calcula el Índice de Masa Corporal.

## Pantallas
1. **Formulario**: Nombre, Sexo (Hombre/Mujer), Peso (kg), Estatura (cm) y botón **Calcular IMC**.
2. **Resultado**: nombre, IMC, ilustración según sexo y complexión, valor cualitativo y botón **Volver**.

## Validaciones
- Nombre: obligatorio.
- Sexo: obligatorio.
- Peso: entre 30 y 250 kg (rechaza valores no razonables como 500 kg o 22 kg).
- Estatura: entre 100 y 230 cm.

Los errores se muestran debajo de cada campo en rojo.

## Cálculo
IMC = peso (kg) / estatura (m)²

| IMC | Valor cualitativo | Ilustración |
|---|---|---|
| < 18,5 | Bajo peso | flaco/a |
| 18,5 – 24,9 | Peso normal | normal |
| 25 – 29,9 | Sobrepeso | gordo/a |
| ≥ 30 | Obesidad | gordo/a |

## Listas inmutables
Las ilustraciones se guardan en dos listas inmutables (`listOf`):
- `ILUSTRACIONES_HOMBRE`: hombre_flaco, hombre_normal, hombre_gordo
- `ILUSTRACIONES_MUJER`: mujer_flaca, mujer_normal, mujer_gorda

## Estructura
- `MainActivity.kt`: Activity y navegación entre pantallas.
- `Imc.kt`: lógica (rangos, listas, cálculo y valor cualitativo).
- `PantallaFormulario.kt`: pantalla 1.
- `PantallaResultado.kt`: pantalla 2.

## Navegación
Se usa un único estado `enSegunda` (Boolean): si es `false` se muestra el formulario y si es `true` el resultado.

## Autor
Sergio Álvarez – 2º DAM