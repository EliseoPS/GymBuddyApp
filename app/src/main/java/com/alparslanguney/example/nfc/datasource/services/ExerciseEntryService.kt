package com.alparslanguney.example.nfc.datasource.services

import com.alparslanguney.example.nfc.models.ExcerciseEntry
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ExerciseEntryService {
    @POST("excercise_entry")
    suspend fun createExerciseEntry(@Body entry: ExcerciseEntry): Response<Unit>
}