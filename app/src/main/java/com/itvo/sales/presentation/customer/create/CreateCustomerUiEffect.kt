package com.itvo.sales.presentation.customer.create

sealed class CreateCustomerUiEffect {
    data object NavigateBack : CreateCustomerUiEffect()
    data class ShowError(val message: String) : CreateCustomerUiEffect()
    data class ShowSuccess(val message: String) : CreateCustomerUiEffect()
}