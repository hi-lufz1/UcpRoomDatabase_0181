package com.example.ucproomdb.ui.customwidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucproomdb.R

@Composable
fun TopAppBar(
    onBack: () -> Unit,
    showBackButton: Boolean = true,
    judul: String ,
    modifier: Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center // Pastikan konten di tengah
    ) {
        if (showBackButton) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack, // Fungsi untuk ikon kembali
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_round_left__streamline_core),
                        contentDescription = "Kembali",
                        modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.weight(2f))
                Image(
                    painter = painterResource(id = R.drawable.chillguy),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(42.dp)
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.weight(2f))
                Image(
                    painter = painterResource(id = R.drawable.chillguy),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(42.dp)
                )
            }
        }

        // Teks judul
        Text(
            text = judul,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun TopAppBarPreview() {
    TopAppBar(
        onBack = {}, // Fungsi kosong untuk preview
        judul = "DASHBOARD",
        showBackButton = true, // Optional: default sudah true
        modifier = Modifier // Default Modifier
    )
}