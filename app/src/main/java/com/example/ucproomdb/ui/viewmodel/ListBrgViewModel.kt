package com.example.ucproomdb.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdb.data.entity.Barang
import com.example.ucproomdb.data.repository.RepositoryBrg
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ListBrgViewModel(
    private val
    repositoryBrg: RepositoryBrg
) : ViewModel() {
    val listBrgUiState: StateFlow<BarangUiState> = repositoryBrg.getAllBrg()
        .filterNotNull()
        .map {
            BarangUiState(
                listBrg = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(BarangUiState(isLoading = true))
            delay(500)
        }
        .catch {
            emit(
                BarangUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Error"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BarangUiState(
                isLoading = true
            )
        )
}


data class BarangUiState(
    val listBrg: List<Barang> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)