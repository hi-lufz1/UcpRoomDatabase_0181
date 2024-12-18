package com.example.ucproomdb.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "barang")
data class Barang(
    @PrimaryKey
    val idBarang :String,
    val nama :String,
    val deskripsi :String,
    val harga :Int,
    val stok :Int,
    val namaSupplier :String,
)
