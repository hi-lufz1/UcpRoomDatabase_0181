package com.example.ucproomdb.ui.view.supplier

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.ucproomdb.ui.customwidget.TopAppBar
import com.example.ucproomdb.ui.viewmodel.PenyediaViewModel
import com.example.ucproomdb.ui.viewmodel.supplier.InsertSuppUiState
import com.example.ucproomdb.ui.viewmodel.supplier.InsertSuppViewModel
import com.example.ucproomdb.ui.viewmodel.supplier.SuppFormErrorState
import com.example.ucproomdb.ui.viewmodel.supplier.SupplierEvent
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun FormSupplier(
    supplierEvent: SupplierEvent = SupplierEvent(),
    onValueChange: (SupplierEvent) -> Unit = {},
    errorState: SuppFormErrorState = SuppFormErrorState(),
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.idSupplier,
            onValueChange = {
                onValueChange(supplierEvent.copy(idSupplier = it))
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
            isError = errorState.idSupplier != null,
            placeholder = { Text("Masukkan ID") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = colorResource(id = R.color.graycard),
                errorBorderColor = Color.Red
            ),
        )
        Text(
            text = errorState.idSupplier ?: "",
            color = Color.Red
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.nama,
            onValueChange = {
                onValueChange(supplierEvent.copy(nama = it))
            },
            label = {
                Text(
                    "Nama", color = Color.Black, fontWeight = FontWeight.Bold,
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
            ),
        )
        Text(
            text = errorState.nama ?: "",
            color = Color.Red
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.alamat,
            onValueChange = {
                onValueChange(supplierEvent.copy(alamat = it))
            },
            label = {
                Text(
                    "Alamat", color = Color.Black, fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            textStyle = TextStyle(
                color = Color.Black, fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            isError = errorState.alamat != null,
            placeholder = { Text("Masukkan Alamat") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = colorResource(id = R.color.graycard),
                errorBorderColor = Color.Red
            ),
        )
        Text(
            text = errorState.alamat ?: "",
            color = Color.Red
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = supplierEvent.kontak,
            onValueChange = {
                onValueChange(supplierEvent.copy(kontak = it))
            },
            label = {
                Text(
                    "Kontak", color = Color.Black, fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            textStyle = TextStyle(
                color = Color.Black, fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            isError = errorState.kontak != null,
            placeholder = { Text("Masukkan Kontak") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = colorResource(id = R.color.graycard),
                errorBorderColor = Color.Red
            ),
        )
        Text(
            text = errorState.kontak ?: "",
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InsertSupplierView(
    onNavigate: () -> Unit = {},
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: InsertSuppViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiStateP = viewModel.suppUiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    //Observasi perubahan snackbarMessage
    LaunchedEffect(uiStateP.snackBarMessage) {
        uiStateP.snackBarMessage?.let { message ->
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
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) })
    { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodySupp(
                uiState = uiStateP,
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
    uiState: InsertSuppUiState,
    onClick: () -> Unit
) {
    val isClicked = remember { mutableStateOf(false) }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.user_check_validate__streamline_core),
            contentDescription = "",
            modifier = Modifier.size(92.dp),
            tint = colorResource(id = R.color.primary)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        FormSupplier(
            supplierEvent = uiState.supplierEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier.fillMaxWidth()
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