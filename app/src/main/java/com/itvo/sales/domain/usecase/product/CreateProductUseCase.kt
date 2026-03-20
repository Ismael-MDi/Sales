package com.itvo.sales.domain.usecase.product

import com.itvo.sales.domain.model.Product
import com.itvo.sales.data.local.repository.ProductRepository
import javax.inject.Inject

class CreateProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: Product) {
        repository.saveProduct(product)
    }
}