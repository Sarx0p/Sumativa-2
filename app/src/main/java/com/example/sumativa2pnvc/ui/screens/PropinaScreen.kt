package com.example.sumativa2pnvc.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sumativa2pnvc.ui.components.ResultCard
import com.example.sumativa2pnvc.ui.theme.Sumativa2PNVCTheme

@Composable
fun PropinaScreen(modifier: Modifier = Modifier) {
    // variables de estado
    var platillo by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var porcentaje by remember { mutableStateOf("") }
    var propina by remember { mutableStateOf(0.0) }
    var total by remember { mutableStateOf(0.0) }
    var error by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ResultCard("Propina", propina, Modifier.weight(1f))
            ResultCard("Total a pagar", total, Modifier.weight(1f))
        }

        OutlinedTextField(
            value = platillo,
            onValueChange = { platillo = it },
            label = { Text("Nombre del platillo") },
            singleLine = true,
            isError = error != null && platillo.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = error != null && cantidad.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio unitario") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            isError = error != null && precio.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = porcentaje,
            onValueChange = { porcentaje = it },
            label = { Text("Porcentaje de propina (%)") },
            singleLine = true,
            isError = error != null && porcentaje.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Button(
            onClick = {
                val c = cantidad.toIntOrNull()
                val p = precio.toDoubleOrNull()
                val pct = porcentaje.toDoubleOrNull()
                when {
                    platillo.isBlank() || cantidad.isBlank() || precio.isBlank() || porcentaje.isBlank() ->
                        error = "Todos los campos son obligatorios"
                    c == null || p == null || pct == null ->
                        error = "Digite valores numéricos válidos"
                    c <= 0 || p <= 0 ->
                        error = "La cantidad y el precio deben ser mayores a 0"
                    pct !in 0.0..10.0 ->
                        error = "El porcentaje debe estar entre 0 y 10"
                    else -> {
                        val subtotal = c * p
                        propina = subtotal * pct / 100
                        total = subtotal + propina
                        error = null
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        OutlinedButton(
            onClick = {
                platillo = ""
                cantidad = ""
                precio = ""
                porcentaje = ""
                propina = 0.0
                total = 0.0
                error = null
            },
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpiar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PropinaScreenPreview() {
    Sumativa2PNVCTheme {
        PropinaScreen()
    }
}
