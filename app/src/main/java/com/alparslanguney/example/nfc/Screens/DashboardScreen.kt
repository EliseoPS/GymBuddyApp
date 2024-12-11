package com.example.gymbuddyapp.Screens

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.alparslanguney.example.nfc.Screens.VideoYoutube
import com.alparslanguney.example.nfc.presentation.components.TutorialItem

val tutoriales = com.alparslanguney.example.nfc.util.tutoriales

@Composable
fun DashboardScreen(innerPadding: PaddingValues){
    val lifecycleOwner = LocalLifecycleOwner.current
    val videos = listOf(
        "EKUNGQ4LmH8" to "Video de introducción al gimnasio",
        "ygFvuqi5b7M" to "Tutorial de ejercicios para principiantes",
        "geK9GNKGRpo" to "Tips para un entrenamiento efectivo"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LazyColumn(
            contentPadding = innerPadding,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Video Tutoriales:",
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
            }

            items(videos.size) { index ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                ) {
                    Text(
                        text = videos[index].second,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Start)
                    )
                    VideoYoutube(
                        urlVideo = videos[index].first,
                        lifecycleOwner = lifecycleOwner
                    )
                }
            }
            item {
                Text(
                    text = "Tutoriales GymBuddy:",
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(20.dp))
            }


            items(tutoriales.size){ index ->
                TutorialItem(tutoriales[index])
                Spacer(Modifier.height(20.dp))
            }
        }
    }

}