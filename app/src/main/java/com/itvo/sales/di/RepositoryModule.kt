package com.itvo.sales.di

import com.itvo.sales.data.local.remote.FirestoreCustomerRepository
import com.itvo.sales.data.local.remote.FirestoreProductRepository
import com.itvo.sales.data.local.repository.CustomerRepository
import com.itvo.sales.data.local.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        repository: FirestoreProductRepository
    ): ProductRepository


    @Binds
    @Singleton
    abstract fun bindCustomerRepository(
        repository: FirestoreCustomerRepository
    ): CustomerRepository
}
