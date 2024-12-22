package com.example.ucproomdb.data.repository

import com.example.ucproomdb.data.dao.BarangDao
import com.example.ucproomdb.data.entity.Barang
import kotlinx.coroutines.flow.Flow

class LocalRepoBrg(
    private val barangDao: BarangDao
) : RepositoryBrg {

    override suspend fun insertBrg(barang: Barang) {
        barangDao.insertBarang(barang)
    }

    override fun getAllBrg(): Flow<List<Barang>> {
       return barangDao.getAllBarang()
    }

    override fun getBrg(idBarang: String): Flow<Barang> {
       return barangDao.getBarang(idBarang)
    }

    override suspend fun deleteBrg(barang: Barang) {
        barangDao.deleteBarang(barang)
    }

    override suspend fun updateBrg(barang: Barang) {
        barangDao.updateBarang(barang)
    }

}