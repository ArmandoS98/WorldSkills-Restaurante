package com.aesc.visaappk.provider.services.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aesc.restaurantews.provider.services.models.LoginState
import com.aesc.restaurantews.provider.services.models.Registro
import com.aesc.restaurantews.provider.services.models.Respuesta
import com.aesc.restaurantews.provider.services.models.User
import com.aesc.restaurantews.provider.services.repository.MainRepository
import kotlinx.coroutines.*
import retrofit2.Response


/**
 * Fuentes
 * https://medium.com/android-news/kotlin-coroutines-and-retrofit-e0702d0b8e8f
 * https://howtodoandroid.com/mvvm-retrofit-recyclerview-kotlin/
 * https://github.com/velmurugan-murugesan/Android-Example/tree/master/MVVMwithKotlinCoroutinesandRetrofit/app
 * https://howtodoandroid.com/mvvm-kotlin-coroutines-retrofit/
 * https://programmerclick.com/article/66871206516/
 * */
class MainViewModel : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val errorMessageLoginAPI = MutableLiveData<Any>()
    val responseAPI = MutableLiveData<Respuesta>()
    val responseLoginAPI = MutableLiveData<Any>()
    val responseLoginAPIv2 = MutableLiveData<LoginState>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun registrar(jv: Registro) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = MainRepository().getStatusRegister(jv)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    responseAPI.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    fun login(credentials: User) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = MainRepository().login(credentials)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && validateResponse(response)) {
                    val temp = response.body()
                    val body = temp.toString()
                    responseLoginAPI.postValue(temp!!)
                    loading.value = false
                } else {
                    onError(response.body().toString())
                }
            }
        }
    }

    fun loginv2(credentials: User) {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = MainRepository().loginv2(credentials)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful && validateResponse(response)) {
                    val temp = response.body()
                    responseLoginAPIv2.postValue(temp!!)
                    loading.value = false
                } else {
                    onError(response.body()!!.mensaje.toString())
                }
            }
        }
    }


    private fun validateResponse(response: Response<*>): Boolean {
        val json = response.body().toString()
        //{respuesta=ERROR, mensaje=Datos de acceso incorrectos}
        return !json.contains("respuesta=ERROR")
    }

    private fun onError(message: String) {
        try {
            errorMessage.value = message
            loading.value = false
        } catch (ex: Throwable) {
            println("Catching ex in runFailingCoroutine(): $ex")
        }

    }

    private fun onErrorLogin(message: String) {
        try {
            errorMessage.value = message
            loading.value = false
        } catch (ex: Throwable) {
            println("Catching ex in runFailingCoroutine(): $ex")
        }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}
