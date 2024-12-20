package com.example.ucproomdb.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.ucproomdb.ui.view.HomeView
import com.example.ucproomdb.ui.view.supplier.InsertSupplierView

@Composable
fun PengelolaHalaman(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiInsertSupp.route
    ) {
        composable(route = DestinasiHome.route) {
            HomeView(
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
    }
}