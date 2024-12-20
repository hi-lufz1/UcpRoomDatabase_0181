package com.example.ucproomdb.ui.viewmodel

import com.example.ucproomdb.data.entity.Supplier


data class MhsUIState(
    val supplierEvent: SupplierEvent = SupplierEvent(),
    val isEntryValid: SupFormErrorState = SupFormErrorState(),
    val snackBarMessage: String? = null
)

data class SupFormErrorState(
    val idSupplier: String? = null,
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
) {
    fun isValid(): Boolean {
        return idSupplier == null && nama == null &&
        kontak== null && alamat == null
    }
}

fun SupplierEvent.toSupplierEntity(): Supplier = Supplier(
    idSupplier = idSupplier,
    nama = nama,
    kontak = kontak,
    alamat = alamat
)

data class SupplierEvent(
    val idSupplier: String = "",
    val nama: String = "",
    val kontak: String = "",
    val alamat: String = ""
)

