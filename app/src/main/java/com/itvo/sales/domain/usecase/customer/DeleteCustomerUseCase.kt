package com.itvo.sales.domain.usecase.customer

import com.itvo.sales.data.local.repository.CustomerRepository
import javax.inject.Inject

class DeleteCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(customerId: String) {
        repository.deleteCustomer(customerId)
    }
}