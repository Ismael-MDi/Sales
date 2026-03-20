package com.itvo.sales.presentation.customer.list

import com.itvo.sales.domain.model.Customer

data class ListCustomerUiState(
    val customers: List<Customer> = emptyList(),
    val isLoading: Boolean = false
)