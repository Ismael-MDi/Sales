package com.itvo.sales.presentation.product.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.itvo.sales.domain.model.Product
import com.itvo.sales.domain.usecase.product.ListProductsUseCase
import com.itvo.sales.domain.usecase.product.DeleteProductUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListProductViewModel @Inject constructor(
    getProductsUseCase: ListProductsUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : ViewModel() {

    val uiState: StateFlow<ListProductUiState> =
        getProductsUseCase()
            .map { products ->
                ListProductUiState(
                    isLoading = false,
                    products = products
                )
            }
            .onStart {
                emit(ListProductUiState(isLoading = true))
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5_000),
                ListProductUiState()
            )

    fun deleteProduct(code: String) {
        viewModelScope.launch {
            deleteProductUseCase(code)
        }
    }
}

data class ListProductUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList()
)