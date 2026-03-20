package com.itvo.sales.data.local.repository

import com.itvo.sales.domain.model.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun getCustomers(): Flow<List<Customer>>

    suspend fun findCustomerById(id: String): Customer?

    suspend fun saveCustomer(customer: Customer)

    suspend fun deleteCustomer(id: String)
}