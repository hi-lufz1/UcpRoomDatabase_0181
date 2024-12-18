package com.example.ucproomdb.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucproomdb.data.entity.Supplier
import kotlinx.coroutines.flow.Flow


@Dao
interface SupplierDao {

    @Insert
    suspend fun insertSupplier(
        supplier: Supplier
    )

    @Query("SELECT * FROM supplier ORDER BY nama ASC")
    fun getAllSupplier(): Flow<List<Supplier>>
}