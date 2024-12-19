package com.example.ucproomdb.ui.viewmodel

import com.example.ucproomdb.data.entity.Barang





data class BarangUiState(
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)