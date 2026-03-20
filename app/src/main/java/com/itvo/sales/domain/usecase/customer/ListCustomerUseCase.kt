package com.itvo.sales.domain.usecase.customer


import com.itvo.sales.domain.model.Customer
import com.itvo.sales.data.local.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ListCustomerUseCase @Inject constructor(
    private val repository: CustomerRepository
) {
    operator fun invoke(): Flow<List<Customer>> {
        return repository.getCustomers()
    }
}