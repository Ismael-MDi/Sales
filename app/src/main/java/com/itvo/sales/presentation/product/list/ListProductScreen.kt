package com.itvo.sales.presentation.product.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.graphics.Color // Asegúrate de importar Color
import androidx.compose.ui.unit.sp

@Composable
fun ListProductScreen(
    viewModel: ListProductViewModel = hiltViewModel(),
    onNavigateToCreate: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // 1. Variable para saber qué producto queremos borrar (null significa que no hay diálogo)
    var productToDelete by remember { mutableStateOf<String?>(null) }

    // 2. Si la variable tiene un código, dibujamos el AlertDialog
    if (productToDelete != null) {
        AlertDialog(
            onDismissRequest = { productToDelete = null }, // Si tocan fuera, se cancela
            title = { Text("Eliminar producto") },
            text = { Text("¿Estás seguro de que deseas eliminar este producto? Esta acción no se puede deshacer.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.deleteProduct(productToDelete!!) // Borramos
                        productToDelete = null // Cerramos el diálogo
                    }
                ) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { productToDelete = null }) { // Solo cerramos
                    Text("Cancelar")
                }
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToCreate) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar Producto"
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when {
                uiState.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        // Un texto enorme para saber si se quedó cargando infinitamente
                        Text("CARGANDO...", color = Color.Red, fontSize = 30.sp)
                    }
                }
                uiState.products.isEmpty() -> {
                    // Un texto enorme para saber si cree que no hay productos
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("¡LA LISTA ESTÁ VACÍA!", color = Color.Yellow, fontSize = 30.sp)
                    }
                }
                else -> {
                    ListProduct(
                        products = uiState.products,
                        onDeleteClick = { productCode ->
                            productToDelete = productCode
                        }
                    )
                }
            }
        }
    }
}