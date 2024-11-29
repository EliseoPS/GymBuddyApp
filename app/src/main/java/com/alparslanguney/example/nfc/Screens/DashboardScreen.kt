package com.example.gymbuddyapp.Screens

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

val videoUrl = "https://www.youtube.com/embed/YB6PwKTnKnw?si=ql3Gmql17jJp0Ntj"

@Composable
fun DashboardScreen(innerPadding: PaddingValues){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        YouTubeVideoPlayer(videoUrl)



    }
}

@Composable
fun YouTubeVideoPlayer(videoUrl: String) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            settings.javaScriptEnabled = true // Habilitar JavaScript
            settings.pluginState = WebSettings.PluginState.ON
            webViewClient = WebViewClient() // Evitar que abra el navegador externo
            loadUrl(videoUrl) // Cargar la URL del video
        }
    })
}