package com.example.ucproomdb.ui.view.barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdb.ui.customwidget.DropDownWidget
import com.example.ucproomdb.ui.viewmodel.PenyediaViewModel
import com.example.ucproomdb.ui.viewmodel.barang.BarangEvent
import com.example.ucproomdb.ui.viewmodel.barang.FormErrorState
import com.example.ucproomdb.ui.viewmodel.barang.InsertBrgUiState
import com.example.ucproomdb.ui.viewmodel.barang.InsertBrgViewModel
import kotlinx.coroutines.launch


@Preview(showBackground = true)
@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorState: FormErrorState = FormErrorState(),
    supplierList: List<String> = emptyList(),
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.idBarang,
            onValueChange = {
                onValueChange(barangEvent.copy(idBarang = it))
            },
            label = { Text("ID") },
            isError = errorState.idBarang != null,
            placeholder = { Text("Masukkan ID") }
        )
        Text(
            text = errorState.idBarang ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
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
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = { Text("Deskripsi") },
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukkan Deskripsi") }
        )
        Text(
            text = errorState.deskripsi ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga.toString(),
            onValueChange = { input ->
                val harga = input.toIntOrNull()
                if (harga != null) {
                    onValueChange(barangEvent.copy(harga = harga)) // Simpan sebagai Int
                } else if (input.isEmpty()) {
                    // Jika input kosong, atur ke nilai default (misalnya 0)
                    onValueChange(barangEvent.copy(harga = 0))
                }
            },
            label = { Text("Harga") },
            isError = errorState.harga != null,
            placeholder = { Text("Masukkan Harga") }
        )
        Text(
            text = errorState.harga ?: "",
            color = Color.Red
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok.toString(),
            onValueChange = { input ->
                val stok = input.toIntOrNull()
                if (stok != null) {
                    onValueChange(barangEvent.copy(stok = stok)) // Simpan sebagai Int
                } else if (input.isEmpty()) {
                    // Jika input kosong, atur ke nilai default (misalnya 0)
                    onValueChange(barangEvent.copy(stok = 0))
                }
            },
            label = { Text("Stok") },
            isError = errorState.stok != null,
            placeholder = { Text("Masukkan Stok") }
        )
        Text(
            text = errorState.stok ?: "",
            color = Color.Red
        )

        DropDownWidget(
            selectedValue = barangEvent.namaSupplier,
            options = supplierList.map { it }, // Asumsikan Supplier memiliki properti namaSupplier
            label = "Supplier",
            onValueChangeEvent = { selectedSupplier ->
                onValueChange(barangEvent.copy(namaSupplier = selectedSupplier))
            }
        )


    }

}

@Composable
fun InsertBarangView(
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertBrgViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiStateB = viewModel.brgUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val supplierList = viewModel.supplierList.collectAsState(initial = emptyList()).value

    //Observasi perubahan snackbarMessage
    LaunchedEffect(uiStateB.snackBarMessage) {
        uiStateB.snackBarMessage?.let { message ->
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
            InsertBodyBrg(
                uiState = uiStateB,
                onValueChange = { viewModel.updateState(it) },
                supplierList = supplierList.map { it.nama },
                onClick = {
                    viewModel.saveData()
                    onNavigate()
                })
        }
    }
}


@Composable
fun InsertBodyBrg(
    modifier: Modifier = Modifier,
    onValueChange: (BarangEvent) -> Unit,
    supplierList: List<String>,
    uiState: InsertBrgUiState,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            supplierList = supplierList // Ekstrak nama supplier
        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}

