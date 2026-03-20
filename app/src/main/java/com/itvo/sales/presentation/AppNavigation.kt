package com.itvo.sales.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itvo.sales.presentation.customer.create.CreateCustomerScreen
import com.itvo.sales.presentation.customer.list.ListCustomerScreen
import com.itvo.sales.presentation.product.create.CreateProductScreen
import com.itvo.sales.presentation.product.list.ListProductScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Obtenemos la ruta actual para saber qué botón pintar de color
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Esta es la barra que te pidió el profesor
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Productos") },
                    label = { Text("Productos") },
                    selected = currentRoute == "product_list" || currentRoute == "create_product",
                    onClick = {
                        navController.navigate("product_list") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Person, contentDescription = "Clientes") },
                    label = { Text("Clientes") },
                    selected = currentRoute == "customer_list" || currentRoute == "create_customer",
                    onClick = {
                        navController.navigate("customer_list") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "product_list",
            modifier = Modifier.padding(paddingValues)
        ) {
            // ---------- MÓDULO PRODUCTOS ----------
            composable("product_list") {
                ListProductScreen(
                    onNavigateToCreate = { navController.navigate("create_product") }
                )
            }
            composable("create_product") {
                CreateProductScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            // MÓDULO CLIENTES
            composable("customer_list") {
                ListCustomerScreen(
                    onNavigateToCreate = { navController.navigate("create_customer") }
                )
            }
            composable("create_customer") {
                CreateCustomerScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}