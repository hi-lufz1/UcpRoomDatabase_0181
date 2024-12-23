package com.example.ucproomdb.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucproomdb.R

@Composable
fun BottomBar(
    onHome: () -> Unit,
    onBarang: () -> Unit,
    onSupplier: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 36.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem(
            onClick = onHome,
            iconResId = R.drawable.home_3__streamline_core,
            contentDescription = "Home",
            label = "Home"
        )
        Spacer(modifier = Modifier.weight(2f))
        BottomBarItem(
            onClick = onBarang,
            iconResId = R.drawable.download_box_1__streamline_core,
            contentDescription = "Barang",
            label = "Barang"
        )
        Spacer(modifier = Modifier.weight(2f))
        BottomBarItem(
            onClick = onSupplier,
            iconResId = R.drawable.user_multiple_circle__streamline_core,
            contentDescription = "Supplier",
            label = "Supplier"
        )
    }
}

@Composable
fun BottomBarItem(
    onClick: () -> Unit,
    iconResId: Int,
    contentDescription: String,
    label: String
) {
    Column (
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = contentDescription,
                modifier = Modifier.size(32.dp)
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BottomPreview() {
    BottomBar(
        onHome = {},
        onBarang = {},// Fungsi kosong untuk preview
        onSupplier = {}, // Optional: default sudah true
        modifier = Modifier // Default Modifier
    )
}
