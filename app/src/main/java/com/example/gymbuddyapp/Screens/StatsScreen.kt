package com.example.gymbuddyapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(innerPadding: PaddingValues){
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    val options = listOf("Opción 1", "Opción 2", "Opción 3")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Column(
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = "Selecciona el nombre del ejercicio",
                color = MaterialTheme.colorScheme.onSurface
            )
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                TextField(
                    value = selectedOption,
                    onValueChange = { selectedOption = it },
                    readOnly = true, // Hace que solo se seleccione de la lista
                    label = { Text("Selecciona un ejercicio") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    colors = TextFieldDefaults.colors() // Corrección aquí
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(option) },
                            onClick = {
                                selectedOption = option
                                expanded = false // Cierra el menú después de seleccionar
                            }
                        )
                    }
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.Start
        ){
//            AsyncImage(
//                model = ,
//                contentDescription = ,
//            )
        }

    }
}