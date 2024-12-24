package com.example.ucproomdb.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucproomdb.ui.customwidget.BottomBar
import com.example.ucproomdb.ui.customwidget.TopAppBar
import com.example.ucproomdb.R

@Preview(showBackground = true)
@Composable
fun HomeView(
    onBarang: () -> Unit = {},
    onSupplier: () -> Unit = {},
    onAddBarang: () -> Unit = {},
    onAddSupplier: () -> Unit = {},
    onHome: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                onBack = onAddSupplier,
                judul = "Hi, Good Morning!",
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Highlight Card
            HighlightCard(
                title = "INVENTORY",
                subtitle = "Management",
                iconResId = R.drawable.warehouse_1__streamline_core,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Menu Grid
            LazyVerticalGrid (
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
            ) {
                item {
                    MenuItem(
                        title = "Barang",
                        iconResId = R.drawable.download_box_1__streamline_core,
                        onClick = onBarang
                    )
                }
                item {
                    MenuItem(
                        title = "Supplier",
                        iconResId = R.drawable.user_multiple_circle__streamline_core,
                        onClick = onSupplier
                    )
                }
                item {
                    MenuItem(
                        title = "Tambah Barang",
                        iconResId = R.drawable.shipment_add__streamline_core,
                        onClick = onAddBarang
                    )
                }
                item {
                    MenuItem(
                        title = "Tambah Supplier",
                        iconResId = R.drawable.nurse_assistant_emergency__streamline_core,
                        onClick = onAddSupplier
                    )
                }
            }
        }
    }
}

@Composable
fun HighlightCard(
    title: String,
    subtitle: String,
    iconResId: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(colorResource(id = R.color.primary), colorResource(id = R.color.secondary))
                ),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = subtitle,
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.7f)
            )
        }
    }
}

@Composable
fun MenuItem(
    title: String,
    iconResId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card (
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable (onClick = onClick)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = colorResource(id = R.color.primary),
                fontWeight = FontWeight.SemiBold

            )

        }
    }
}
