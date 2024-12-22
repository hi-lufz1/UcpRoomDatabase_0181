package com.example.ucproomdb.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdb.data.entity.Barang
import com.example.ucproomdb.data.repository.RepositoryBrg
import com.example.ucproomdb.ui.navigation.DestinasiUpdateBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg
) : ViewModel() {
    var updateUIState by mutableStateOf(InsertBrgUiState())
        private set

    private val _idBrg: String = checkNotNull(savedStateHandle[DestinasiUpdateBrg.idBarang])

    init {
        viewModelScope.launch {
            updateUIState = repositoryBrg.getBrg(_idBrg)
                .filterNotNull()
                .first()
                .toUiStateBrg()
        }
    }

    fun updateState(barangEvent: BarangEvent) {
        updateUIState = updateUIState.copy(
            barangEvent = barangEvent
        )
    }

    fun validateFields(): Boolean {
        val event = updateUIState.barangEvent
        val errorState = FormErrorState(
            idBarang = if (event.idBarang.isNotEmpty()) null else "ID Barang tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "deskripsi tidak boleh kosong",
            harga = if (event.harga > 0) null else "Harga harus lebih dari 0",
            stok = if (event.stok >= 0) null else "Stok tidak boleh negatif",
            namaSupplier = if (event.namaSupplier.isNotEmpty()) null else "Nama supplier tidak boleh kosong",
        )

        updateUIState = updateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData() {
        val currentEvent = updateUIState.barangEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryBrg.updateBrg(currentEvent.toBarangEntity())
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("snackBarMessage diatur: ${updateUIState.snackBarMessage}")
                } catch (e: Exception) {
                    updateUIState = updateUIState.copy(
                        snackBarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else {
            updateUIState = updateUIState.copy(
                snackBarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        updateUIState = updateUIState.copy(snackBarMessage = null)
    }
}


fun Barang.toUiStateBrg(): InsertBrgUiState = InsertBrgUiState(
    barangEvent = this.toDetailBrgUiEvent()
)