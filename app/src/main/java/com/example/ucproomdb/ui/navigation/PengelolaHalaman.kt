package com.example.ucproomdb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import com.example.ucproomdb.ui.view.HomeView
import com.example.ucproomdb.ui.view.barang.DetailBarangView
import com.example.ucproomdb.ui.view.barang.InsertBarangView
import com.example.ucproomdb.ui.view.barang.ListBarangView
import com.example.ucproomdb.ui.view.barang.UpdateBarangView
import com.example.ucproomdb.ui.view.supplier.InsertSupplierView
import com.example.ucproomdb.ui.view.supplier.ListSupplierView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route
    ) {
        composable(route = DestinasiHome.route) {
            HomeView(
                onHome = { navController.navigate(DestinasiHome.route) },
                onSupplier = { navController.navigate(DestinasiListSupp.route) },
                onBarang = { navController.navigate(DestinasiListBrg.route) },
                onAddSupplierBtn = { navController.navigate(DestinasiInsertSupp.route) },
                onAddBarangBtn = { navController.navigate(DestinasiInsertBrg.route) },
            )
        }
        composable(route = DestinasiInsertSupp.route) {
            InsertSupplierView(
                onNavigate = { navController.navigate(DestinasiHome.route) },
                modifier = modifier
            )
        }
        composable(route = DestinasiInsertBrg.route) {
            InsertBarangView(
                onNavigate = { navController.navigate(DestinasiHome.route) },
                modifier = modifier
            )
        }
        composable(route = DestinasiListBrg.route) {
            ListBarangView(
                onDetailClick = { id ->
                    navController.navigate("${DestinasiDetailBrg.route}/$id")
                    println(
                        "PengelolaHalaman: ID =  $id"
                    )
                },
                onHome = { navController.navigate(DestinasiHome.route) },
                onSupplier = { navController.navigate(DestinasiListSupp.route) },
                onBarang = { navController.navigate(DestinasiListBrg.route) },
                onAddBarang = { navController.navigate(DestinasiInsertBrg.route) },
                modifier = modifier
            )
        }
        composable(route = DestinasiDetailBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiDetailBrg.idBarang) {
                    type = NavType.StringType
                }
            )
        ) {
            val id = it.arguments?.getString(DestinasiDetailBrg.idBarang)
            id?.let { id ->
                DetailBarangView(
                    onBack = {
                        navController.popBackStack()
                    },
                    onEditClick = {
                        navController.navigate("${DestinasiUpdateBrg.route}/$it")
                    },
                    modifier = modifier,
                    onDeleteClick = {
                        navController.popBackStack()
                    }
                )
            }
        }
        composable(
            DestinasiUpdateBrg.routesWithArg,
            arguments = listOf(
                navArgument(DestinasiUpdateBrg.idBarang) {
                    type = NavType.StringType
                }
            )
        ) {
            UpdateBarangView(
                onNavigate = {
                    navController.popBackStack()
                },
                onBack = {navController.popBackStack()},
                modifier = modifier
            )
        }
        composable(route = DestinasiListSupp.route) {
            ListSupplierView(
                onHome = { navController.navigate(DestinasiHome.route) },
                onSupplier = { navController.navigate(DestinasiListSupp.route) },
                onBarang = { navController.navigate(DestinasiListBrg.route) },
                onAddSupplier = {navController.navigate(DestinasiInsertSupp.route)},
                modifier = modifier
            )
        }
    }
}