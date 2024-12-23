package com.example.ucproomdb.ui.view.barang

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdb.data.entity.Barang
import com.example.ucproomdb.ui.customwidget.BottomBar
import com.example.ucproomdb.ui.customwidget.TopAppBar
import com.example.ucproomdb.ui.viewmodel.PenyediaViewModel
import com.example.ucproomdb.ui.viewmodel.barang.BarangUiState
import com.example.ucproomdb.ui.viewmodel.barang.ListBrgViewModel
import kotlinx.coroutines.launch
import com.example.ucproomdb.R

@Preview(showBackground = true)
@Composable
fun ListBarangView(
    viewModel: ListBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onDetailClick: (String) -> Unit = { },
    onAddBarang: () -> Unit = { },
    onBack: () -> Unit = {},
    onHome: () -> Unit = {},
    onBarang: () -> Unit = {},
    onSupplier: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                onBack = onBack,
                judul = "Daftar Barang",
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
                onClick = onAddBarang,
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp),
                containerColor = colorResource(id = R.color.primary),
                contentColor = Color.White
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_layer_2__streamline_core_remix),
                    contentDescription = "Tambah Barang",
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    ) { innerPadding ->
        val listBrgUiState by viewModel.listBrgUiState.collectAsState()

        BodyListBarang(
            listBrgUiState = listBrgUiState,
            onClick = {
                onDetailClick(it)
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@Composable
fun BodyListBarang(
    listBrgUiState: BarangUiState,
    onClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when {
        listBrgUiState.isLoading -> {
            // indikator loading
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            )
            {
                CircularProgressIndicator()
            }
        }

        listBrgUiState.isError -> {
            LaunchedEffect(listBrgUiState.errorMessage) {
                listBrgUiState.errorMessage?.let { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)  //tampilkan snackbar
                    }
                }
            }
        }

        listBrgUiState.listBrg.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tidak ada data barang.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListBarang(
                listBrg = listBrgUiState.listBrg,
                onClick = {
                    onClick(it)
                    println(it)
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListBarang(
    listBrg: List<Barang>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
) {
    LazyColumn(
        modifier = modifier
    )
    {
        items(
            items = listBrg,
            itemContent = { brg ->
                CardBrg(
                    brg = brg,
                    onClick = { onClick(brg.idBarang) }
                )
            }
        )
    }
}

@Composable
fun CardBrg(
    brg: Barang,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    val cardColor = when {
        brg.stok == 0 -> colorResource(id = R.color.graycard)
        brg.stok in 1..10 -> colorResource(id = R.color.redcard)
        else -> colorResource(id = R.color.greencard)
    }
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        shape = RoundedCornerShape(16.dp)

    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth()
        ) {
            Icon(
                painter = painterResource(id = R.drawable.shipment_remove__streamline_core),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 12.dp)
                    .size(48.dp),
                tint = Color.White
            )

            Column(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .weight(1f)
            )
            {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                    Text(
                        text = brg.nama,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.White,
                    )
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Spacer(modifier = Modifier.padding(4.dp))
                    Text(
                        text = "Harga", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.7f)
                    )
                    Text(
                        text = " : ", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.1f)
                    )
                    Text(
                        text = "Rp." + brg.harga.toString(),
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
                        text = "Stok", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.7f)
                    )
                    Text(
                        text = " : ", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.1f)
                    )
                    Text(
                        text = brg.stok.toString(),
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp,
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
                        text = "Supplier", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.7f)
                    )
                    Text(
                        text = " : ", fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp, modifier = Modifier.weight(0.1f)
                    )
                    Text(
                        text = brg.namaSupplier,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(2f)
                    )

                }
            }
            Icon(
                painter = painterResource(id = R.drawable.bracket__streamline_core),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp)
                    .padding(horizontal = 12.dp)
                    .fillMaxHeight(),
                tint = Color.White
            )

        }

    }
}