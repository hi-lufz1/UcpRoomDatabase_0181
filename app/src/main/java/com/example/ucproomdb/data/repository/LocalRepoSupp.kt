package com.example.ucproomdb.data.repository

import com.example.ucproomdb.data.dao.SupplierDao
import com.example.ucproomdb.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

class LocalRepoSupp (
    private val supplierDao: SupplierDao
): RepositorySupp {
    override suspend fun insertSupp(supplier: Supplier) {
        supplierDao.insertSupplier(supplier)
    }

    override fun getAllSupp(): Flow<List<Supplier>> {
        return supplierDao.getAllSupplier()
    }
}