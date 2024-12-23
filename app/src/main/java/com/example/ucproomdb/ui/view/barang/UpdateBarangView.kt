package com.example.ucproomdb.ui.view.barang

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdb.ui.customwidget.TopAppBar
import com.example.ucproomdb.ui.viewmodel.PenyediaViewModel
import com.example.ucproomdb.ui.viewmodel.barang.InsertBrgViewModel
import com.example.ucproomdb.ui.viewmodel.barang.UpdateBrgViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UpdateBarangView(
    onNavigate: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModelList: InsertBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    viewModel: UpdateBrgViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.updateUIState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val supplierList = viewModelList.supplierList.collectAsState(initial = emptyList()).value

    //Observasi perubahan snackbarMessage
    LaunchedEffect(uiState.snackBarMessage) {
        println("LaunchedEffect triggered")
        uiState.snackBarMessage?.let { message ->
            println("Snackbar message received: $message")
            coroutineScope.launch {
                println("Launching coroutine for snackbar")
                snackbarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Long
                )
                viewModel.resetSnackBarMessage()
            }
        }
    }
    Scaffold( topBar = {
        TopAppBar(
            onBack = onBack,
            judul = "Edit Barang",
            showBackButton = true,
            modifier = modifier
        )
    },modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) })
    { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            InsertBodyBrg(
                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent) // Update state di ViewModel
                },
                supplierList = supplierList.map { it.nama },
                onClick = {
                    coroutineScope.launch {
                        if (viewModel.validateFields()) {
                            viewModel.updateData()
                            delay(600)
                            withContext(Dispatchers.Main) {
                                onNavigate() //Navigasi di main thread
                            }
                        }
                    }
                })
        }
    }
}