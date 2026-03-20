package com.itvo.sales.presentation.product.create

data class CreateProductUiState(
    val code: String = "",
    val description: String = "",
    val category: String = "",
    val price: String = "", // Mantenemos como String para el TextField
    val stock: String = "", // Mantenemos como String para el TextField
    val taxable: Boolean = false,
    val isLoading: Boolean = false
)