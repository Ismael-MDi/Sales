package com.itvo.sales.presentation.customer.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itvo.sales.domain.usecase.customer.DeleteCustomerUseCase
import com.itvo.sales.domain.usecase.customer.ListCustomerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCustomerViewModel @Inject constructor(
    listCustomerUseCase: ListCustomerUseCase,
    private val deleteCustomerUseCase: DeleteCustomerUseCase
) : ViewModel() {

    val state: StateFlow<ListCustomerUiState> = listCustomerUseCase()
        .map { ListCustomerUiState(customers = it) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            ListCustomerUiState(isLoading = true)
        )

    fun deleteCustomer(id: String) {
        viewModelScope.launch {
            deleteCustomerUseCase(id)
        }
    }
}