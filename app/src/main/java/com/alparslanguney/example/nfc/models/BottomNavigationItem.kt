package com.alparslanguney.example.nfc.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.alparslanguney.example.nfc.R
import com.alparslanguney.example.nfc.util.Dumbbell
import com.alparslanguney.example.nfc.util.GraphUp
import com.alparslanguney.example.nfc.util.HamburgerMenu
import com.alparslanguney.example.nfc.util.Screens

data class BottomNavigationItem(
    val title : String,
    val icon : ImageVector,
    val route : String
){
    companion object{
        val items = listOf(
            BottomNavigationItem(
                title = "Ejercicios",
                icon = Dumbbell,
                route = Screens.ExerciseSelectScreen.route
            ),
            BottomNavigationItem(
                title = "Estadísticas",
                icon = GraphUp,
                route = Screens.StatsScreen.route
            ),
            BottomNavigationItem(
                title = "Dashboard",
                icon = HamburgerMenu,
                route = Screens.DashboardScreen.route
            ),

        )
    }
}