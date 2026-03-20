package com.itvo.sales.presentation.product.create

sealed class CreateProductUiEffect {
    data class ShowError(val message: String) : CreateProductUiEffect()
    data class ShowSuccess(val message: String) : CreateProductUiEffect()
    data object NavigateBack : CreateProductUiEffect() // Actualizado a 'data object'
}