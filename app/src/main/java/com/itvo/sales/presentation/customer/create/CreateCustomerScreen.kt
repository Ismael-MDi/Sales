package com.itvo.sales.presentation.customer.create

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
@Composable
fun CreateCustomerScreen(
    viewModel: CreateCustomerViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                CreateCustomerUiEffect.NavigateBack -> onNavigateBack()
                is CreateCustomerUiEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
                is CreateCustomerUiEffect.ShowSuccess -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.id,
                onValueChange = { viewModel.onEvent(CreateCustomerUiEvent.IdChanged(it)) },
                label = { Text("ID del Cliente") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.name,
                onValueChange = { viewModel.onEvent(CreateCustomerUiEvent.NameChanged(it)) },
                label = { Text("Nombre Completo") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEvent(CreateCustomerUiEvent.EmailChanged(it)) },
                label = { Text("Correo Electrónico") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = state.phone,
                onValueChange = { viewModel.onEvent(CreateCustomerUiEvent.PhoneChanged(it)) },
                label = { Text("Teléfono") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { viewModel.onEvent(CreateCustomerUiEvent.SaveClicked) },
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
            ) {
                Text("Guardar Cliente")
            }
        }
    }
}