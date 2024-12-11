package com.alparslanguney.example.nfc.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alparslanguney.example.nfc.models.Entrada
import com.alparslanguney.example.nfc.ui.theme.GymBuddyAppTheme
import com.alparslanguney.example.nfc.util.Dumbbell

@Composable
fun EjercicioItem(ejercicio: Entrada){

    Row(
        modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(24.dp)
        )
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(2f)
        ){

            Text(
                text = "Ejercicio: " + ejercicio.nombre,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 17.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Reps: " + ejercicio.reps,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Peso: " + ejercicio.peso,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "El: " + ejercicio.fecha,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
        ){
            Icon(
                imageVector = Dumbbell,
                modifier = Modifier.size(54.dp),
                contentDescription = "icono pesa"
            )
        }
    }
}

@Preview
@Composable
fun EjercicioItemPreview(){
    val ejercicio = Entrada(nombre = "Press banca",fecha = "2024-10-10", reps = 10, peso = 20f)
    GymBuddyAppTheme {
        EjercicioItem(ejercicio)
        }
}