package com.itvo.sales.domain.usecase.product

import com.itvo.sales.data.local.repository.ProductRepository
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productCode: String) {
        repository.deleteProduct(productCode)
    }
}