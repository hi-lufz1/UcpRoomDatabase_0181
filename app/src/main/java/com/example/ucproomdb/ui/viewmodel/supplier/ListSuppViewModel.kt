package com.example.ucproomdb.ui.viewmodel.supplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucproomdb.data.entity.Barang
import com.example.ucproomdb.data.entity.Supplier
import com.example.ucproomdb.data.repository.RepositoryBrg
import com.example.ucproomdb.data.repository.RepositorySupp
import com.example.ucproomdb.ui.viewmodel.barang.BarangUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class ListSuppViewModel(
    private val
    repositorySupp: RepositorySupp
) : ViewModel() {
    val listSuppUiState: StateFlow<SuppUiState> = repositorySupp.getAllSupp()
        .filterNotNull()
        .map {
            SuppUiState(
                listSupp = it.toList(),
                isLoading = false
            )
        }
        .onStart {
            emit(SuppUiState(isLoading = true))
            delay(500)
        }
        .catch {
            emit(
                SuppUiState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?: "Error"
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = SuppUiState(
                isLoading = true
            )
        )
}


data class SuppUiState(
    val listSupp: List<Supplier> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)
