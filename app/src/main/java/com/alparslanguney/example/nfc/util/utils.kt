package com.alparslanguney.example.nfc.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter


data class Tutorial(
    val title : String,
    val content : String,
    val image : String
)

val tutoriales = listOf(
    Tutorial(
        title = "Bench Press",
        content = "Acostado en un banco, toma la barra con ambas manos. Baja lentamente la barra hasta el pecho y luego empuja hacia arriba. Mantén los codos ligeramente doblados en la parte superior.",
        image = "https://static.strengthlevel.com/images/exercises/bench-press/bench-press-800.jpg"
    ),
    Tutorial(
        title = "Sentadilla",
        content = "Con los pies a la altura de los hombros, baja las caderas manteniendo la espalda recta. Regresa a la posición inicial, empujando con los talones.",
        image = "https://static.strengthlevel.com/images/exercises/squat/squat-800.jpg"
    ),
    Tutorial(
        title = "Peso Muerto",
        content = "Con una barra en el suelo, inclínate desde la cadera, toma la barra con ambas manos y levántala hasta que estés completamente erguido. Baja la barra de forma controlada.",
        image = "https://static.strengthlevel.com/images/exercises/deadlift/deadlift-800.jpg"
    ),
    Tutorial(
        title = "Dominadas",
        content = "Con las palmas hacia adelante, agarra la barra y levanta el cuerpo hasta que el mentón pase la barra. Baja lentamente para completar la repetición.",
        image = "https://static.strengthlevel.com/images/exercises/pull-ups/pull-ups-800.jpg"
    )
)