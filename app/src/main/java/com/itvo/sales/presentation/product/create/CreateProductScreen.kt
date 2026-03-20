package com.itvo.sales.presentation.product.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CreateProductScreen(
    viewModel: CreateProductViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                CreateProductUiEffect.NavigateBack -> onNavigateBack()
                is CreateProductUiEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
                is CreateProductUiEffect.ShowSuccess -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues -> // Usamos los paddingValues del Scaffold

        Column(
            modifier = Modifier
                .fillMaxSize() // Hacemos que la columna ocupe toda la pantalla
                .padding(paddingValues) // Respetamos los márgenes del sistema
                .padding(horizontal = 24.dp, vertical = 16.dp), // Damos margen a los lados
            verticalArrangement = Arrangement.spacedBy(12.dp), // Espaciado automático entre elementos
            horizontalAlignment = Alignment.CenterHorizontally // Centramos todo
        ) {

            OutlinedTextField(
                value = state.code,
                onValueChange = { viewModel.onEvent(CreateProductUiEvent.CodeChanged(it)) },
                label = { Text("Code") },
                modifier = Modifier.fillMaxWidth() // Estiramos el campo al ancho disponible
            )

            OutlinedTextField(
                value = state.description,
                onValueChange = { viewModel.onEvent(CreateProductUiEvent.DescriptionChanged(it)) },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.category,
                onValueChange = { viewModel.onEvent(CreateProductUiEvent.CategoryChanged(it)) },
                label = { Text("Categoría") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.price,
                onValueChange = { viewModel.onEvent(CreateProductUiEvent.PriceChanged(it)) },
                label = { Text("Precio") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.stock,
                onValueChange = { viewModel.onEvent(CreateProductUiEvent.StockChanged(it)) },
                label = { Text("Disponibilidad") },
                modifier = Modifier.fillMaxWidth()
            )

            // Sección del Switch
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "¿Aplica impuesto?",
                    style = MaterialTheme.typography.bodyLarge
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Switch(
                        checked = state.taxable,
                        onCheckedChange = { isChecked ->
                            viewModel.onEvent(CreateProductUiEvent.TaxableChanged(isChecked))
                        }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(if (state.taxable) "Sí" else "No")
                }
            }

            Spacer(modifier = Modifier.weight(1f)) // Este Spacer empuja el botón "Save" hacia abajo

            Button(
                onClick = { viewModel.onEvent(CreateProductUiEvent.SaveClicked) },
                modifier = Modifier
                    .fillMaxWidth() // Botón ancho
                    .padding(bottom = 16.dp)
            ) {
                Text("Save")
            }
        }
    }
}