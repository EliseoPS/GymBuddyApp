package com.example.gymbuddyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gymbuddyapp.Screens.*
import com.example.gymbuddyapp.models.BottomNavigationItem
import com.example.gymbuddyapp.ui.theme.GymBuddyAppTheme
import com.example.gymbuddyapp.utils.Screens
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.indendshape.ShapeCornerRadius
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            var selectedItem by rememberSaveable{
                mutableIntStateOf(0)
            }

            GymBuddyAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        AnimatedNavigationBar(
                            selectedIndex = 0,
                            modifier = Modifier
                                .height(90.dp),
                            barColor = Color(0xFFFF931F),
                            cornerRadius = shapeCornerRadius(cornerRadius = 34.dp)
                        ){
                            BottomNavigationItem.items.forEachIndexed { index, bottomNavigationItem ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            selectedItem = index
                                            navController.navigate(bottomNavigationItem.route)
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ){
                                    Icon(
                                        imageVector = bottomNavigationItem.icon,
                                        contentDescription = bottomNavigationItem.title,
                                        tint = if (selectedItem == index) Color.White else Color.White.copy(alpha = 0.5f),
                                        modifier = Modifier.size(26.dp)
                                    )
                                    Text(
                                        text = bottomNavigationItem.title,
                                        color = if (selectedItem == index) Color.White else Color.White.copy(alpha = 0.5f)
                                    )
                                }
                            }


                        }
                    }
                ) { innerPadding ->
                    NavHost(navController = navController, startDestination = Screens.LoginScreen.route){
                        composable(route = Screens.LoginScreen.route){
                            LoginScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screens.StatsScreen.route){
                            StatsScreen(innerPadding = innerPadding)
                        }
                        composable(route = Screens.DashboardScreen.route){
                            DashboardScreen(innerPadding = innerPadding)
                        }
                        composable(route = Screens.ExerciseSelectScreen.route){
                            ExerciseSelectScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screens.ManualEntryScreen.route){
                            ManualEntryScreen(innerPadding = innerPadding, navController = navController)
                        }
                        composable(route = Screens.NFCEntryScreen.route){
                            NFCEntryScreen(innerPadding = innerPadding, navController = navController)
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GymBuddyAppTheme {
        Greeting("Android")
    }
}