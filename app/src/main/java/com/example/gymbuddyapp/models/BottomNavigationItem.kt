package com.example.gymbuddyapp.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gymbuddyapp.R
import com.example.gymbuddyapp.utils.Screens

data class BottomNavigationItem(
    val title : String,
    val icon : ImageVector,
    val route : String
){
    companion object{
        val items = listOf(
            BottomNavigationItem(
                title = "Inicio",
                icon = Icons.Default.Home,
                route = Screens.HomeScreen.route
            ),
            BottomNavigationItem(
                title = "Ejercicios",
                icon = Icons.Default.Home,
                route = Screens.ExerciseSelectScreen.route
            ),
            BottomNavigationItem(
                title = "Dashboard",
                icon = Icons.Default.Home,
                route = Screens.DashboardScreen.route
            ),

        )
    }
}