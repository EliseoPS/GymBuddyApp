package com.example.gymbuddyapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.alparslanguney.example.nfc.R
import com.alparslanguney.example.nfc.util.Screens
import kotlinx.coroutines.delay

@Composable
fun NFCEntryScreen(innerPadding: PaddingValues, navController: NavController){
    var showImage by remember { mutableStateOf(false) }
    var isButtonClicked by remember { mutableStateOf(false) }

    LaunchedEffect(isButtonClicked) {
        if (isButtonClicked) {
            delay(2000)
            showImage = true // No se pudo hacer la funcionalidad NFC con la API, por ahora se dejó una demo de como funcionará
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Acerca tu celular a la etiqueta NFC")

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(10.dp)
                .clickable {
                    navController.navigate(Screens.NFCEntryScreen.route)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.nfcbutton),
                contentDescription = "rutinas automaticas",
                modifier = Modifier.size(200.dp)
            )

            Button(onClick = {
                isButtonClicked = true
            }) {
                Text(
                    text = "Escanear",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (showImage) {
                Image(
                    painter = painterResource(id = R.drawable.exito),
                    contentDescription = "Imagen después de esperar",
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(15.dp))

                Text(
                    text = "Ejercicio capturado!",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun NFCEntryScreenPreview(){
    NFCEntryScreen(innerPadding = PaddingValues(0.dp), navController = rememberNavController())
}