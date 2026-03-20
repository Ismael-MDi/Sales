package com.itvo.sales.data.local.remote

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.itvo.sales.domain.model.Customer
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomerFirebaseDataSource @Inject constructor() {
    private val firestore = FirebaseFirestore.getInstance()
    private val collection = firestore.collection("customers")
    init {
        Log.d("DEBUG_SALES", "CustomerDataSource conectado al proyecto: ${firestore.app.options.projectId}")
    }
    fun getCustomers(): Flow<List<Customer>> = callbackFlow {
        val listener = collection.addSnapshotListener { querySnapshot, firebaseError ->
            if (firebaseError != null) {
                Log.e("DEBUG_SALES", "Error Clientes: ${firebaseError.message}")
                close(firebaseError)
                return@addSnapshotListener
            }
            if (querySnapshot != null) {
                try {
                    val customers = querySnapshot.toObjects(Customer::class.java)
                    Log.d("DEBUG_SALES", "Clientes cargados: ${customers.size}")
                    trySend(customers).isSuccess
                } catch (e: Exception) {
                    Log.e("DEBUG_SALES", "Error mapeo clientes: ${e.message}")
                }
            }
        }
        awaitClose { listener.remove() }
    }
    suspend fun saveCustomer(customer: Customer) {
        try {
            collection.document(customer.id).set(customer).await()
            Log.d("DEBUG_SALES", "Cliente guardado con éxito: ${customer.name}")
        } catch (e: Exception) {
            Log.e("DEBUG_SALES", "Error al guardar cliente: ${e.message}")
            throw e
        }
    }
    suspend fun findCustomerById(customerId: String): Customer? {
        return try {
            val doc = collection.document(customerId).get().await()
            if (doc.exists()) {
                doc.toObject(Customer::class.java)
            } else {
                Log.w("DEBUG_SALES", "No existe cliente con ID: $customerId")
                null
            }
        } catch (e: Exception) {
            Log.e("DEBUG_SALES", "Error al buscar cliente: ${e.message}")
            null
        }
    }
    suspend fun deleteCustomer(customerId: String) {
        try {
            collection.document(customerId).delete().await()
            Log.d("DEBUG_SALES", "Cliente eliminado: $customerId")
        } catch (e: Exception) {
            Log.e("DEBUG_SALES", "Error al eliminar cliente: ${e.message}")
            throw e
        }
    }
}