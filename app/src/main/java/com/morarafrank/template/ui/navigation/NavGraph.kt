package com.morarafrank.template.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.morarafrank.template.ui.screens.AddMenuScreen
import com.morarafrank.template.ui.screens.MenusScreen
import com.morarafrank.template.ui.screens.UpdateMenu


sealed class MenusNav(val route: String){
    object MenusScreen: MenusNav("menus")
    
    object UpdateMenusScreen: MenusNav("update")
    
    object AddMenusScreen: MenusNav("add")
}
@Composable
fun MenusNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController, 
        startDestination = MenusNav.MenusScreen.route){
        
        composable(MenusNav.MenusScreen.route){
            MenusScreen(
                navigateToAddMenuScreen = {
                    navController.navigate(
                        MenusNav.AddMenusScreen.route
                    )
                },
                navigateToUpdateMenuScreen = {menuId ->
                    navController.navigate(
                        route = MenusNav.UpdateMenusScreen.route + "/$menuId"
                    )
                }
            )
        }

        composable(MenusNav.AddMenusScreen.route){
            AddMenuScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = "${MenusNav.UpdateMenusScreen.route}/{menuId}",
            arguments = listOf(
                navArgument("menuId"){
                    type = NavType.IntType
                }
            )
        ){ navBackStackEntry ->
            val menuId = navBackStackEntry.arguments?.getInt("menuId")
            if (menuId != null) {
                UpdateMenu(
                    navigateBack = {
                        navController.popBackStack()
                    },
                    menuId = menuId
                )
            }
        }
    }
    
}