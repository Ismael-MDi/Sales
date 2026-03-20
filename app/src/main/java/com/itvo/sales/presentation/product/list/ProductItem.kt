package com.itvo.sales.presentation.product.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itvo.sales.domain.model.Product

@Composable
fun ProductItem(
    product: Product,
    onDeleteClick: () -> Unit // Esta es la acción que mandará a borrar el producto
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                // Aquí mostramos los datos del producto
                Text(
                    text = product.description, // Cambia 'description' por el nombre de tu variable si es diferente
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Código: ${product.code} | Precio: $${product.price}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Este es el botón del basurero
            IconButton(onClick = onDeleteClick) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar producto",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}