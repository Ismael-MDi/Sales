package com.itvo.sales.data.local.remote
import com.itvo.sales.data.local.repository.CustomerRepository
import com.itvo.sales.domain.model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestoreCustomerRepository @Inject constructor(
    private val customerFirebaseDataSource: CustomerFirebaseDataSource
) : CustomerRepository {

    override fun getCustomers(): Flow<List<Customer>> {
        return customerFirebaseDataSource.getCustomers()
    }

    override suspend fun findCustomerById(id: String): Customer? {
        return customerFirebaseDataSource.findCustomerById(id)
    }

    override suspend fun saveCustomer(customer: Customer) {
        customerFirebaseDataSource.saveCustomer(customer)
    }

    override suspend fun deleteCustomer(id: String) {
        customerFirebaseDataSource.deleteCustomer(id)
    }
}