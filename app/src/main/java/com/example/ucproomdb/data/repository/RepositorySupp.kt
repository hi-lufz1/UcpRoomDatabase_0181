package com.example.ucproomdb.data.repository

import com.example.ucproomdb.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

interface RepositorySupp {

    suspend fun insertSupp (supplier: Supplier)

    fun getAllSupp(): Flow<List<Supplier>>
}