package com.example.ucproomdb

import android.app.Application
import com.example.ucproomdb.dependeciesinjection.ContainerApp

class InventoryApp : Application() {
    lateinit var containerApp: ContainerApp

    override fun onCreate() {
        super.onCreate()
        containerApp = ContainerApp (this)
    }
}