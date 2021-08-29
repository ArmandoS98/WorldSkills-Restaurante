package com.aesc.visaappk.provider.services.repository

import com.aesc.restaurantews.provider.services.models.Registro
import com.aesc.visaappk.provider.services.api.MyRetrofitBuilder

class MainRepository {
    suspend fun getStatusRegister(jv: Registro) = MyRetrofitBuilder.apiService.registrarUsuario(jv)
}