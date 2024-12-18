package com.example.ucproomdb.data.repository

import com.example.ucproomdb.data.entity.Barang
import kotlinx.coroutines.flow.Flow

interface RepositoryBrg {

    suspend fun insertBrg (barang: Barang)

    fun getAllBrg(): Flow<List<Barang>>

    fun getBrg(idBarang: String): Flow<Barang>

    suspend fun deleteBrg (barang: Barang)

    suspend fun updateMhs (barang: Barang)

}