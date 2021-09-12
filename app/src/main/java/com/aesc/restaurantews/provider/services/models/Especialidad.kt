package com.aesc.restaurantews.provider.services.models

data class Especialidad(
    val respuesta: String? = "",
    val datos: Datos? = null
)

data class Datos(
    val nombre: String? = "",
    val descripcion: String? = "",
    val precio: Long? = 21400,
    val url_foto: String? = ""
)
