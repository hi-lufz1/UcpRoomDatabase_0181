package com.example.ucproomdb.ui.view.barang

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucproomdb.R
import com.example.ucproomdb.data.entity.Barang
import com.example.ucproomdb.ui.customwidget.TopAppBar
import com.example.ucproomdb.ui.viewmodel.PenyediaViewModel
import com.example.ucproomdb.ui.viewmodel.barang.DetailBrgUiState
import com.example.ucproomdb.ui.viewmodel.barang.DetailBrgViewModel
import com.example.ucproomdb.ui.viewmodel.barang.toBarangEntity


@Composable
fun DetailBarangView(
    modifier: Modifier = Modifier,
    viewModel: DetailBrgViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onBack: () -> Unit = { },
    onEditClick: (String) -> Unit = { },
    onDeleteClick: () -> Unit = { }
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                onBack = onBack,
                judul = "Detail Barang",
                showBackButton = true,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditClick(viewModel.detailUiState.value.detailUiEvent.idBarang) },
                shape = CircleShape,
                modifier = Modifier
                    .padding(8.dp)
                    .size(64.dp),
                containerColor = colorResource(id = R.color.primary),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Barang",
                    modifier = Modifier.size(36.dp)
                )
            }
        }
    ) { innerPadding ->
        val detailBrgUiState by viewModel.detailUiState.collectAsState()

        BodyDetailBrg(
            modifier = Modifier.padding(innerPadding),
            detailBrgUiState = detailBrgUiState,
            onDeleteClick = {
                viewModel.deleteBrg()
                onDeleteClick()
            }
        )
    }
}


//@Preview(showBackground = true)
@Composable
fun BodyDetailBrg(
    modifier: Modifier = Modifier,
    detailBrgUiState: DetailBrgUiState = DetailBrgUiState(),
    onDeleteClick: () -> Unit = {}
) {
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
    when {
        detailBrgUiState.isLoading -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                //tampilkan loading
                CircularProgressIndicator()
            }
        }

        detailBrgUiState.isUiEventNotEmpty -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            {
                ItemDetailBrg(
                    barang = detailBrgUiState.detailUiEvent.toBarangEntity(),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Button(
                    onClick = {
                        deleteConfirmationRequired = true
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                {
                    Text(text = "Delete")
                }

                if (deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            onDeleteClick()
                        },
                        onDeleteCancel = {
                            deleteConfirmationRequired = false
                        }, modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        detailBrgUiState.isUiEventEmpty -> {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data tidak ditemukan",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardDetailBrg(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.shipment_remove__streamline_core),
                contentDescription = "",
                modifier = Modifier
                    .size(48.dp),
                tint = colorResource(id = R.color.primary)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = "placeholderNama",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(8.dp)
                )
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.insert_top_left__streamline_core),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(16.dp),
                        tint = colorResource(id = R.color.primary)
                    )
                    Text(
                        text = "placeholderID",
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                Row(
                    modifier = modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.user_check_validate__streamline_core),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .size(16.dp),
                        tint = colorResource(id = R.color.primary)
                    )
                    Text(
                        text = "placeholderSupplier",
                        fontSize = 16.sp,
                        color = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.padding(bottom = 8.dp))
            }

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(160.dp)

        ) {
            Text(
                text = "Deskripsi",
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Deskripsi",
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.padding(4.dp))
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Column (modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Harga",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "placeholder harga",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),

                ) {
                Column (modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Stok",
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(8.dp)

                    )
                    Text(
                        text = "placeholderstok",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                }
            }
        }
    }

}


@Composable
fun ItemDetailBrg(
    modifier: Modifier = Modifier,
    barang: Barang
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailBrg(judul = "ID", isinya = barang.idBarang)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(judul = "Nama", isinya = barang.nama)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(judul = "Deskripsi", isinya = barang.deskripsi)
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(judul = "Harga", isinya = barang.harga.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(judul = "Stok", isinya = barang.stok.toString())
            Spacer(modifier = Modifier.padding(4.dp))
            ComponentDetailBrg(judul = "Supplier", isinya = barang.namaSupplier)
        }
    }
}

@Composable
fun ComponentDetailBrg(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    )
    {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Tidak ada aksi */ },
        modifier = modifier,
        containerColor = Color(0xFF1C1C1E), // Warna latar belakang dialog
        title = null, // Kita buat judul manual
        confirmButton = {}, // Kosong karena kita membuat tombol custom
        dismissButton = {},
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Judul
                Text(
                    text = "Delete Data",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally) // Pusatkan teks judul
                )

                // Pesan
                Text(
                    text = "Apakah anda yakin ingin menghapus data?",
                    fontSize = 16.sp,
                    color = Color.LightGray, // Warna teks deskripsi
                    modifier = Modifier.padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )

                // Garis pemisah horizontal
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.fillMaxWidth()
                )

                // Baris tombol
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Tombol Cancel
                    TextButton(
                        onClick = onDeleteCancel,
                        modifier = Modifier.weight(1f),

                        ) {
                        Text(
                            text = "Cancel",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Garis pemisah vertikal
                    Divider(
                        color = Color.Gray,
                        thickness = 1.dp,
                        modifier = Modifier
                            .height(48.dp)
                            .width(1.dp)
                    )

                    // Tombol Leave
                    TextButton(
                        onClick = onDeleteConfirm,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "Delete",
                            color = colorResource(id = R.color.primary),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        },
    )
}

