package com.aesc.restaurantews.provider.services.models

import com.google.gson.annotations.SerializedName

data class Registro(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("correo")
    val correo: String,
    @SerializedName("contrasena")
    val contrasena: String,
    @SerializedName("ciudad")
    val ciudad: String
)