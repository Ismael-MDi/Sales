package com.itvo.sales.domain.usecase.customer

import com.itvo.sales.domain.model.Customer
import com.itvo.sales.data.local.repository.CustomerRepository
import javax.inject.Inject

class CreateCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    suspend operator fun invoke(customer: Customer) {
        repository.saveCustomer(customer)
    }
}