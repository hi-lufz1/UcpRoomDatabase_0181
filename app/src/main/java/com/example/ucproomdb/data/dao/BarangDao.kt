package com.example.ucproomdb.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucproomdb.data.entity.Barang
import kotlinx.coroutines.flow.Flow


@Dao
interface BarangDao {

    @Insert
    suspend fun insertBarang(
        barang: Barang
    )

    @Query("SELECT * FROM barang ORDER BY nama ASC")
    fun getAllBarang(): Flow<List<Barang>>

    @Query("SELECT * FROM barang WHERE idBarang = :idBarang")
    fun getBarang(idBarang: String): Flow<Barang>

    @Delete
    suspend fun deleteBarang(
        barang: Barang
    )
    @Update
    suspend fun updateBarang(
        barang: Barang
    )
}