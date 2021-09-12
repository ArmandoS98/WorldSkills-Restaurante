package com.aesc.restaurantews.provider.services.models

data class Politicas(
    val respuesta: String? = "",
    val datos: DatosPoliticas? = null
)

data class DatosPoliticas(
    val politicas: String? = ""
)
