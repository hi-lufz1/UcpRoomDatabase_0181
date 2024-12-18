package com.example.ucproomdb.dependeciesinjection

import android.content.Context
import com.example.ucproomdb.data.database.InventoryDB
import com.example.ucproomdb.data.repository.LocalRepoBrg
import com.example.ucproomdb.data.repository.LocalRepoSupp
import com.example.ucproomdb.data.repository.RepositoryBrg
import com.example.ucproomdb.data.repository.RepositorySupp

interface InterfaceContainerApp{
    val repositoryBrg: RepositoryBrg
    val repositorySupp: RepositorySupp
}

class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryBrg: RepositoryBrg by lazy {
        LocalRepoBrg(InventoryDB.getDatabase(context).barangDao())
    }

    override val repositorySupp: RepositorySupp by lazy {
        LocalRepoSupp(InventoryDB.getDatabase(context).supplierDao())
    }
}