package com.example.ucproomdb.ui.viewmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ucproomdb.InventoryApp
import com.example.ucproomdb.ui.viewmodel.barang.InsertBrgViewModel
import com.example.ucproomdb.ui.viewmodel.supplier.InsertSuppViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            InsertBrgViewModel(
                inventoryApp().containerApp.repositoryBrg,
                inventoryApp().containerApp.repositorySupp
            )
        }
        initializer {
            InsertSuppViewModel(
                inventoryApp().containerApp.repositorySupp
            )
        }
    }
}

fun CreationExtras.inventoryApp(): InventoryApp =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as InventoryApp)