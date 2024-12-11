package com.alparslanguney.example.nfc.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alparslanguney.example.nfc.models.Entrada
import com.alparslanguney.example.nfc.ui.theme.GymBuddyAppTheme
import com.alparslanguney.example.nfc.util.Tutorial

@Composable
fun TutorialItem(tutorial: Tutorial){

    Card(
        modifier = Modifier.fillMaxHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ){
        Column(
            modifier = Modifier.padding(20.dp)
        ){
            Text(
                text = tutorial.title,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(Modifier.height(23.dp))

            Text(
                text = tutorial.content,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.height(23.dp))

            AsyncImage(
                model = tutorial.image,
                contentDescription = "Imagen tutorial",
                modifier = Modifier.clip(RoundedCornerShape(16.dp))
            )
        }
    }

}

@Preview
@Composable
fun TutorialItemPreview(){
    val tutorial = Tutorial(
        title = "Bench Press",
        content = "Acostado en un banco, toma la barra con ambas manos. Baja lentamente la barra hasta el pecho y luego empuja hacia arriba. Mantén los codos ligeramente doblados en la parte superior.",
        image = "https://static.strengthlevel.com/images/exercises/bench-press/bench-press-800.jpg"
    )
    GymBuddyAppTheme {
        TutorialItem(tutorial)
    }
}