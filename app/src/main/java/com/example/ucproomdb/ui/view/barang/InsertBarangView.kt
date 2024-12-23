package com.example.ucproomdb.ui.view.barang

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdb.R
import com.example.ucproomdb.ui.customwidget.DropDownWidget
import com.example.ucproomdb.ui.customwidget.TopAppBar
import com.example.ucproomdb.ui.viewmodel.PenyediaViewModel
import com.example.ucproomdb.ui.viewmodel.barang.BarangEvent
import com.example.ucproomdb.ui.viewmodel.barang.FormErrorState
import com.example.ucproomdb.ui.viewmodel.barang.InsertBrgUiState
import com.example.ucproomdb.ui.viewmodel.barang.InsertBrgViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
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
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.idBarang,
            onValueChange = {
                onValueChange(barangEvent.copy(idBarang = it))
            },
            label = {
                Text(
                    "ID", color = Color.Black, fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            textStyle = TextStyle(
                color = Color.Black, fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            isError = errorState.idBarang != null,
            placeholder = { Text("Masukkan ID") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = colorResource(id = R.color.graycard),
                errorBorderColor = Color.Red
            ),
        )
        Text(
            text = errorState.idBarang ?: "",
            color = Color.Red
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = {
                onValueChange(barangEvent.copy(nama = it))
            },
            label = {
                Text(
                    "Nama",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            textStyle = TextStyle(
                color = Color.Black, fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = colorResource(id = R.color.graycard),
                errorBorderColor = Color.Red
            )
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = {
                onValueChange(barangEvent.copy(deskripsi = it))
            },
            label = {
                Text(
                    "Deskripsi",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            textStyle = TextStyle(
                color = Color.Black, fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukkan Deskripsi") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = colorResource(id = R.color.graycard),
                errorBorderColor = Color.Red
            )
        )
        Text(
            text = errorState.deskripsi ?: "",
            color = Color.Red
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.harga.toString(),
            onValueChange = { input ->
                val harga = input.toIntOrNull()
                if (harga != null) {
                    onValueChange(barangEvent.copy(harga = harga))
                } else if (input.isEmpty()) {
                    onValueChange(barangEvent.copy(harga = 0))
                }
            },
            label = {
                Text(
                    "Harga",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }, textStyle = TextStyle(
                color = Color.Black, fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            isError = errorState.harga != null,
            placeholder = { Text("Masukkan Harga") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = colorResource(id = R.color.graycard),
                errorBorderColor = Color.Red
            )
        )
        Text(
            text = errorState.harga ?: "",
            color = Color.Red
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = barangEvent.stok.toString(),
            onValueChange = { input ->
                val stok = input.toIntOrNull()
                if (stok != null) {
                    onValueChange(barangEvent.copy(stok = stok))
                } else if (input.isEmpty()) {
                    onValueChange(barangEvent.copy(stok = 0))
                }
            },
            label = {
                Text(
                    "Stok",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }, textStyle = TextStyle(
                color = Color.Black, fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            isError = errorState.stok != null,
            placeholder = { Text("Masukkan Stok") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = colorResource(id = R.color.graycard),
                errorBorderColor = Color.Red
            )
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

@Preview(showBackground = true)
@Composable
fun InsertBarangView(
    onNavigate: () -> Unit = {},
    onBack: () -> Unit = {},
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
    Scaffold(
        topBar = {
            TopAppBar(
                onBack = onBack,
                judul = "Tambah Barang",
                showBackButton = true,
                modifier = modifier
            )
        },
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
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
    val isClicked = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.shipment_remove__streamline_core),
            contentDescription = "",
            modifier = Modifier.size(92.dp),
            tint = colorResource(id = R.color.primary)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            supplierList = supplierList // Ekstrak nama supplier
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(
                onClick = {
                    onClick()
                    isClicked.value = !isClicked.value
                },
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 6.dp)
                    .border(
                        width = 2.dp,
                        color = if (isClicked.value) colorResource(id = R.color.primary) else Color.Transparent,
                        shape = RoundedCornerShape(8.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isClicked.value) Color.White else colorResource(id = R.color.primary), // warna button
                    contentColor = if (isClicked.value) colorResource(id = R.color.primary) else Color.White // warna text
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Simpan", fontWeight = FontWeight.Bold)
            }
        }
    }
}

