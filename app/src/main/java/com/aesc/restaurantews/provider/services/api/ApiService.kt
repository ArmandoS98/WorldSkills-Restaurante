package com.aesc.restaurantews.provider.services.api

import com.aesc.restaurantews.provider.services.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    //SignUp
    @POST("api/clientes")
    suspend fun registrarUsuario(@Body root: Registro): Response<Respuesta>

    //Login
    @GET("api/clientes?q")
    suspend fun login(
        @Query("correo") order_by: String,
        @Query("contrasena") sort: String
    ): Response<*>

    //Login
    @GET("api/clientes?q")
    suspend fun loginv2(
        @Query("correo") order_by: String,
        @Query("contrasena") sort: String
    ): Response<LoginState>

    //Logo
    @GET("api/logo/1")
    suspend fun getLogo(): Response<Logo>

    //PLATO ESPECIAL DEL RESTAURANTE
    @GET("api/especialidad")
    suspend fun especialidadDelDia(): Response<Especialidad>

    //POLITICAS DE PRIVACIDAD
    @GET("api/politicas?ver")
    suspend fun politicasPrivacidad(): Response<Politicas>

    //CATERGORIAS
    @GET("api/categorias")
    suspend fun categorias(): Response<Categorias>

}