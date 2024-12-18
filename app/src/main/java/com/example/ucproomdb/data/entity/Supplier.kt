package com.example.ucproomdb.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "supplier")
data class Supplier(
    @PrimaryKey
    val idSupplier: String,
    val nama: String,
    val kontak: String,
    val alamat: String

)
