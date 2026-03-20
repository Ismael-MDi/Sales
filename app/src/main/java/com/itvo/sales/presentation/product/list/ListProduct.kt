package com.itvo.sales.presentation.product.list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.itvo.sales.domain.model.Product

@Composable
fun ListProduct(
    products: List<Product>,
    onDeleteClick: (String) -> Unit
) {
    LazyColumn {
        items(products) { product ->
            ProductItem(
                product = product,
                onDeleteClick = { onDeleteClick(product.code) }
            )
        }
    }
}