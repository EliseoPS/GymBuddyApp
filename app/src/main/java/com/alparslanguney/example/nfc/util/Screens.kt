package com.alparslanguney.example.nfc.util

sealed class Screens(val route: String) {
    data object LoginScreen: Screens("login")
    data object HomeScreen: Screens("home")
    data object DashboardScreen: Screens("dashboard")
    data object StatsScreen: Screens("stats")
    data object ExerciseSelectScreen: Screens("selectEx")
    data object NFCEntryScreen: Screens("nfcEntry")
    data object ManualEntryScreen: Screens("manualEntry")

}