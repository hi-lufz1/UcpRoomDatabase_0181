package com.example.ucproomdb.ui.viewmodel.supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdb.data.entity.Supplier
import com.example.ucproomdb.data.repository.RepositorySupp
import kotlinx.coroutines.launch


class InsertSuppViewModel(
    private val repositorySupp: RepositorySupp
) : ViewModel() {

    var suppUiState by mutableStateOf(InsertSuppUiState())

    fun updateState(supplierEvent: SupplierEvent) {
        suppUiState = suppUiState.copy(
            supplierEvent = supplierEvent,
        )
    }

    // Validasi data input pengguna
    private fun validateFields(): Boolean {
        val event = suppUiState.supplierEvent
        val errorState = SuppFormErrorState(
            idSupplier = if (event.idSupplier.isNotEmpty()) null else "ID tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak tidak boleh kosong"

        )

        suppUiState =  suppUiState.copy(isEntryValid =  errorState)
        return errorState.isValid()
    }

    //simpan data ke repository
    fun saveData() {
        val currentEvent = suppUiState.supplierEvent


        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositorySupp.insertSupp(currentEvent.toSupplierEntity())
                    suppUiState = suppUiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        supplierEvent = SupplierEvent(), //Reset input form
                        isEntryValid = SuppFormErrorState() // Reset error state
                    )
                } catch (e: Exception) {
                    suppUiState = suppUiState.copy(
                        snackBarMessage = "Data gagal disimpan",
                    )
                }
            }
        } else {
            suppUiState = suppUiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda",
            )
        }
    }

    fun resetSnackBarMessage() {
        suppUiState = suppUiState.copy(snackBarMessage = null)
    }

}


data class InsertSuppUiState(
    val supplierEvent: SupplierEvent = SupplierEvent(),
    val isEntryValid: SuppFormErrorState = SuppFormErrorState(),
    val snackBarMessage: String? = null
)

data class SuppFormErrorState(
    val idSupplier: String? = null,
    val nama: String? = null,
    val kontak: String? = null,
    val alamat: String? = null
) {
    fun isValid(): Boolean {
        return idSupplier == null && nama == null &&
                kontak == null && alamat == null
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

