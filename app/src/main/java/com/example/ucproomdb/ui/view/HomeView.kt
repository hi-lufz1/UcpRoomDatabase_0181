package com.example.ucproomdb.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ucproomdb.ui.customwidget.BottomBar

@Preview(showBackground = true)
@Composable
fun HomeView(
    onAddBarangBtn: () -> Unit = {},
    onAddSupplierBtn: () -> Unit = {},
    onHome: () -> Unit = {},
    onBarang: () -> Unit = {},
    onSupplier: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            BottomBar(
                onHome = onHome,
                onBarang = onBarang,
                onSupplier = onSupplier,
                modifier = modifier
            )
        }
    ) { innerPadding ->
        // This is where your main content will go
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { onAddBarangBtn() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Barang")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onAddSupplierBtn() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Supplier")
            }
        }
    }
}
