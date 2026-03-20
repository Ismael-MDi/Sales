package com.itvo.sales.data.repository

import com.itvo.sales.domain.model.Customer
import com.itvo.sales.data.local.repository.BaseInMemoryRepository
import com.itvo.sales.data.local.repository.CustomerRepository
import javax.inject.Inject // <-- Import correcto
import javax.inject.Singleton // <-- Import correcto

@Singleton
class InMemoryCustomerRepository @Inject constructor() :
    BaseInMemoryRepository<Customer, String>(emptyList()),
    CustomerRepository {

    override fun getId(item: Customer) = item.id

    override fun getCustomers() = observeAll()

    override suspend fun findCustomerById(id: String)
            = findById(id)

    override suspend fun saveCustomer(customer: Customer)
            = save(customer)

    override suspend fun deleteCustomer(id: String)
            = deleteById(id)
}