package com.example.ucproomdb.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucproomdb.data.dao.BarangDao
import com.example.ucproomdb.data.dao.SupplierDao
import com.example.ucproomdb.data.entity.Barang
import com.example.ucproomdb.data.entity.Supplier


@Database(entities = [Barang::class, Supplier::class], version = 1, exportSchema = false)
abstract class InventoryDB : RoomDatabase(){

    abstract fun barangDao(): BarangDao
    abstract fun supplierDao(): SupplierDao

    companion object{
        @Volatile
        private var Instances: InventoryDB? = null

        fun getDatabase(context: Context): InventoryDB{
            return (Instances ?: synchronized(this){
                Room.databaseBuilder(
                    context,
                    InventoryDB::class.java,
                    "InventoryDB"
                )
                    .build().also { Instances = it }
            })
        }
    }
}