package com.example.ucproomdb.ui.view.supplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdb.ui.viewmodel.InsertSuppViewModel
import com.example.ucproomdb.ui.viewmodel.PenyediaViewModel
import com.example.ucproomdb.ui.viewmodel.SuppFormErrorState
import com.example.ucproomdb.ui.viewmodel.SuppUIState
import com.example.ucproomdb.ui.viewmodel.SupplierEvent
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.text.Normalizer.Form

@Preview(showBackground = true)
@Composable
fun FormSupplier(
    supplierEvent: SupplierEvent = SupplierEvent(),
    onValueChange: (SupplierEvent) -> Unit = {},
    errorState: SuppFormErrorState = SuppFormErrorState(),
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.idSupplier,
            onValueChange = {
                onValueChange(supplierEvent.copy(idSupplier = it))
            },
            label = { Text("ID") },
            isError = errorState.idSupplier != null,
            placeholder = { Text("Masukkan ID") }
        )
        Text(
            text = errorState.idSupplier ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.nama,
            onValueChange = {
                onValueChange(supplierEvent.copy(nama = it))
            },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") }
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.alamat,
            onValueChange = {
                onValueChange(supplierEvent.copy(alamat = it))
            },
            label = { Text("Alamat") },
            isError = errorState.alamat != null,
            placeholder = { Text("Masukkan Alamat") }
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.kontak,
            onValueChange = {
                onValueChange(supplierEvent.copy(kontak = it))
            },
            label = { Text("Kontak") },
            isError = errorState.kontak != null,
            placeholder = { Text("Masukkan Kontak") }
        )
        Text(
            text = errorState.kontak ?: "",
            color = Color.Red
        )


    }

}




@Composable
fun InsertSupplierView(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertSuppViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.suppUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    //Observasi perubahan snackbarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        uiState.snackBarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetSnackBarMessage()
            }
        }
    }


    Scaffold(modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) })
    { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodySupp(
                uiState = uiState,
                onValueChange = { updatedEvent ->
                    viewModel.updateState(updatedEvent)
                },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                }
            )
        }

    }
}


@Composable
fun InsertBodySupp(
    modifier: Modifier = Modifier,
    onValueChange: (SupplierEvent) -> Unit,
    uiState: SuppUIState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormSupplier(
            supplierEvent = uiState.supplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }

}