package com.aesc.restaurantews.provider.services.models

data class LoginState(
    //Success
    val respuesta: String? = "",
    val nombre: String? = "",
    val idCliente: String? = "",
    val token: String? = "",
    //Error
    var mensaje: String? = ""
)