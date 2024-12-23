package com.example.ucproomdb.ui.view.supplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdb.R
import com.example.ucproomdb.data.entity.Supplier
import com.example.ucproomdb.ui.customwidget.BottomBar
import com.example.ucproomdb.ui.customwidget.TopAppBar
import com.example.ucproomdb.ui.viewmodel.PenyediaViewModel
import com.example.ucproomdb.ui.viewmodel.supplier.ListSuppUiState
import com.example.ucproomdb.ui.viewmodel.supplier.ListSuppViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun ListSupplierView(
    viewModel: ListSuppViewModel = viewModel(factory = PenyediaViewModel.Factory),
    modifier: Modifier = Modifier,
    onAddSupplier: () -> Unit = { },
    onBack: () -> Unit = {},
    onHome: () -> Unit = {},
    onBarang: () -> Unit = {},
    onSupplier: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                onBack = onBack,
                judul = "Daftar Supplier",
                showBackButton = false,
                modifier = modifier
            )
        },
        bottomBar = {
            BottomBar(
                onHome = onHome,
                onBarang = onBarang,
                onSupplier = onSupplier,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddSupplier,
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp),
                containerColor = colorResource(id = R.color.primary),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_layer_2__streamline_core_remix),
                    contentDescription = "Tambah Supplier",
                    modifier = Modifier.size(36.dp)
                )
            }
        },  modifier = modifier
    ) { innerPadding ->
        val listSuppUiState by viewModel.listSuppUiState.collectAsState()

        BodyListSupplier(
            listSuppUiState = listSuppUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyListSupplier(
    listSuppUiState: ListSuppUiState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        listSuppUiState.isLoading -> {
            // indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                CircularProgressIndicator()
            }
        }

        listSuppUiState.isError -> {
            LaunchedEffect(listSuppUiState.errorMessage) {
                listSuppUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)  //tampilkan snackbar
                    }
                }
            }
        }

        listSuppUiState.listSupp.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data supplier.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListSupplier(
                listSupp = listSuppUiState.listSupp,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListSupplier(
    listSupp: List<Supplier>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    )
    {
        items(
            items = listSupp,
            itemContent = { supp ->
                CardSupplier(
                    supp = supp,
                )
            }
        )
    }
}


@Composable
fun CardSupplier(
    supp: Supplier,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primary)),

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.user_check_validate__streamline_core),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp)
                    .size(48.dp),
                tint = Color.White
            )
            Column(
                modifier = Modifier.padding(vertical = 12.dp)
                    .weight(1f)
            )
            {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = supp.nama,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.White,
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,

                    )
                {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Alamat", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.6f)
                    )
                    Text(
                        text = " : ", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.1f)
                    )
                    Text(
                        text = supp.alamat,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.weight(2f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Kontak", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.6f)
                    )
                    Text(
                        text = " : ", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.1f)
                    )
                    Text(
                        text = supp.kontak,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
        }
    }
}