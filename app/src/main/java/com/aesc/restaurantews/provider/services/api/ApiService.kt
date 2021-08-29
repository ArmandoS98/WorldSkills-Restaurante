package com.aesc.visaappk.provider.services.api

import com.aesc.restaurantews.provider.services.models.Registro
import com.aesc.restaurantews.provider.services.models.Respuesta
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {
    //Registrar
    @POST("api/clientes")
    suspend fun registrarUsuario(@Body root: Registro): Response<Respuesta>
}