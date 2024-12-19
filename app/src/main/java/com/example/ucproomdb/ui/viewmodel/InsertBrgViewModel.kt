package com.example.ucproomdb.ui.viewmodel

import com.example.ucproomdb.data.entity.Barang


fun BarangEvent.toBaranEntity(): Barang = Barang(
    idBarang = idBarang,
    nama = nama,
    deskripsi =deskripsi,
    harga = harga,
    stok = stok,
    namaSupplier = namaSupplier
)


data class BarangEvent(
    val idBarang :String = "",
    val nama :String = "",
    val deskripsi :String = "",
    val harga :Int = 0,
    val stok :Int = 0,
    val namaSupplier :String = "",
)