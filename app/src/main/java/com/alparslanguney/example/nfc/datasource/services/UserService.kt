package com.alparslanguney.example.nfc.datasource.services

import com.alparslanguney.example.nfc.models.newUser
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UserService {
    @POST("users")
    suspend fun crearNuevaCuneta(@Body nuevaCuenta: newUser) : Response<Unit>

    @GET("nameByID/{id}")
    suspend fun getNombre(@Path("id") id : Int) : Response<String>
}