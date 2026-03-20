package com.itvo.sales.data.local.remote
import com.itvo.sales.data.local.repository.ProductRepository
import com.itvo.sales.domain.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FirestoreProductRepository @Inject constructor(
    private val firebaseDataSource: ProductFirebaseDataSource
) : ProductRepository {

    override fun getProducts(): Flow<List<Product>> {
        return firebaseDataSource.getProducts()
    }

    override suspend fun findProductByCode(productCode: String): Product? {
        return firebaseDataSource.findProductByCode(productCode)
    }

    override suspend fun saveProduct(product: Product) {
        firebaseDataSource.saveProduct(product)
    }

    override suspend fun deleteProduct(productCode: String) {
        firebaseDataSource.deleteProduct(productCode)
    }
}
