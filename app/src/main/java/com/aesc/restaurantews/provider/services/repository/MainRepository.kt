package com.aesc.restaurantews.provider.services.repository

import com.aesc.restaurantews.provider.services.models.Registro
import com.aesc.restaurantews.provider.services.models.User
import com.aesc.restaurantews.provider.services.api.MyRetrofitBuilder

class MainRepository {
    suspend fun getStatusRegister(jv: Registro) = MyRetrofitBuilder.apiService.registrarUsuario(jv)
    suspend fun login(user: User) = MyRetrofitBuilder.apiService.login(user.user, user.pass)
    suspend fun loginv2(user: User) = MyRetrofitBuilder.apiService.loginv2(user.user, user.pass)
    suspend fun logo() = MyRetrofitBuilder.apiService.getLogo()
    suspend fun especialidadDelDia() = MyRetrofitBuilder.apiService.especialidadDelDia()
    suspend fun politicas() = MyRetrofitBuilder.apiService.politicasPrivacidad()
    suspend fun categorias() = MyRetrofitBuilder.apiService.categorias()
}