package com.example.ucproomdb.ui.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdb.data.entity.Barang
import com.example.ucproomdb.data.entity.Supplier
import com.example.ucproomdb.data.repository.RepositoryBrg
import com.example.ucproomdb.data.repository.RepositorySupp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class InsertBrgViewModel(
    private val repositoryBrg: RepositoryBrg,
    private val repositorySupp: RepositorySupp
) : ViewModel() {

    var brgUiState by mutableStateOf(InsertBrgUiState())
    private val _supplierList = MutableStateFlow<List<Supplier>>(emptyList())
    val supplierList = _supplierList.asStateFlow()

    init {
        getSupplierList()
    }

    private fun getSupplierList() {
        viewModelScope.launch {
            repositorySupp.getAllSupp().collect { suppliers ->
                _supplierList.value = suppliers
            }
        }
    }


    fun updateState(barangEvent: BarangEvent) {
        brgUiState = brgUiState.copy(
            barangEvent = barangEvent,
        )
    }

    private fun validateFields(): Boolean {
        val event = brgUiState.barangEvent
        val errorState = FormErrorState(
            idBarang = if (event.idBarang.isNotEmpty()) null else "ID Barang tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "deskripsi tidak boleh kosong",
            harga = if (event.harga > 0) null else "Harga harus lebih dari 0",
            stok = if (event.stok >= 0) null else "Stok tidak boleh negatif",
            namaSupplier = if (event.namaSupplier.isNotEmpty()) null else "Nama supplier tidak boleh kosong",
        )

        brgUiState = brgUiState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun saveData() {
        val currentEvent = brgUiState.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.insertBrg(currentEvent.toBarangEntity())
                    brgUiState = brgUiState.copy(
                        snackBarMessage = "Data berhasil disimpan",
                        barangEvent = BarangEvent(), //Reset input form
                        isEntryValid = FormErrorState() // Reset error state
                    )
                } catch (e: Exception) {
                    brgUiState = brgUiState.copy(
                        snackBarMessage = "Data gagal disimpan",
                    )
                }
            }
        } else {
            brgUiState = brgUiState.copy(
                snackBarMessage = "Input tidak valid. Periksa kembali data Anda",
            )
        }
    }
}

data class InsertBrgUiState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormErrorState = FormErrorState(),
    val snackBarMessage: String? = null
)

data class FormErrorState(
    val idBarang: String? = null,
    val nama: String? = null,
    val deskripsi: String? = null,
    val harga: String? = null,
    val stok: String? = null,
    val namaSupplier: String? = null,
) {
    fun isValid(): Boolean {
        return idBarang == null && nama == null && deskripsi == null &&
                harga == null && stok == null && namaSupplier == null
    }
}

fun BarangEvent.toBarangEntity(): Barang = Barang(
    idBarang = idBarang,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    namaSupplier = namaSupplier
)

data class BarangEvent(
    val idBarang: String = "",
    val nama: String = "",
    val deskripsi: String = "",
    val harga: Int = 0,
    val stok: Int = 0,
    val namaSupplier: String = "",
)