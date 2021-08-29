package com.aesc.restaurantews.provider.services.models

import com.google.gson.annotations.SerializedName

data class Respuesta(
    @SerializedName("respuesta")
    val respuesta: String,
    @SerializedName("mensaje")
    val menssage: String)