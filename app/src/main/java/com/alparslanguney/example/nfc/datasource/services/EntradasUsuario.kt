package com.alparslanguney.example.nfc.datasource.services

import com.alparslanguney.example.nfc.models.Entrada
import com.alparslanguney.example.nfc.models.Excercise
import retrofit2.http.GET
import retrofit2.http.Path

interface EntradasUsuario {
    @GET("excercise_entries_by_user/{user_id}")
    suspend fun getUsersExcercisesById(@Path("user_id") id:Int) : List<Entrada>
}