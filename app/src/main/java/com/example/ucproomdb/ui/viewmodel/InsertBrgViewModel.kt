package com.example.ucproomdb.ui.viewmodel

import com.example.ucproomdb.data.entity.Barang

data class FormErrorState(
    val idBarang :String? = null,
    val nama :String? = null,
    val deskripsi :String? = null,
    val harga :Int? = null,
    val stok :Int? = null,
    val namaSupplier :String? = null,
) {
    fun isValid(): Boolean {
        return idBarang == null && nama == null && deskripsi == null &&
                harga == null && stok == null && namaSupplier == null
    }
}



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