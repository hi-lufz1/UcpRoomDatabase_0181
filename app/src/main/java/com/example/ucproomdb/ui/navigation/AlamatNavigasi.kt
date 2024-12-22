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

object DestinasiListBrg : AlamatNavigasi{
    override val route = "listBrg"
}

object DestinasiDetailBrg : AlamatNavigasi {
    override val route = "detailBrg"
    const val idBarang = "idBarang"
    val routesWithArg = "$route/{$idBarang}"
}

object DestinasiUpdateBrg : AlamatNavigasi {
    override val route = "updateBrg"
    const val idBarang = "idBarang"
    val routesWithArg = "$route/{$idBarang}"
}

object DestinasiInsertSupp : AlamatNavigasi{
    override val route = "insertSupp"
}

object DestinasiListSupp : AlamatNavigasi{
    override val route = "listSupp"
}
