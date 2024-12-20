package com.example.ucproomdb.ui.navigation

interface AlamatNavigasi{
    val route: String
}

object DestinasiHome : AlamatNavigasi{
    override val route = "home"
}

object DestinasiInsertBrg : AlamatNavigasi{
    override val route = "insertBrg"
}

object DestinasiInsertSupp : AlamatNavigasi{
    override val route = "insertSupp"
}