package com.aesc.visaappk.provider.services.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aesc.restaurantews.provider.services.models.*
import com.aesc.restaurantews.provider.services.repository.MainRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
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
    val responseLogo = MutableLiveData<Logo>()
    val responseEspecialidadDia = MutableLiveData<Especialidad>()
    val responsePoliticas = MutableLiveData<Politicas>()
    val responseCategorias = MutableLiveData<Categorias>()
    var job: CompletableJob? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun registrar(jv: Registro) {
        loading.value = true
        job = Job()
        job.let { theJob ->
            CoroutineScope(Dispatchers.IO + theJob!! + exceptionHandler).launch {
                try {
                    val response = MainRepository().getStatusRegister(jv)
                    withContext(Main) {
                        if (response.isSuccessful) {
                            if (validateResponse(response)) {
                                loading.value = false
                                responseAPI.postValue(response.body())
                                theJob.complete()
                            } else {
                                val error = "Error login"
                                onError(error)
                            }
                        } else {
                            val msg = response.message()
                            onError(msg)
                        }
                    }
                } catch (t: Throwable) {
                    val msg = t.message.toString()
                    onError(msg)
                }
            }
        }
    }

    fun politicasPrivacidad() {
        loading.value = true
        job = Job()
        job.let { theJob ->
            CoroutineScope(Dispatchers.IO + theJob!! + exceptionHandler).launch {
                try {
                    val response = MainRepository().politicas()
                    withContext(Main) {
                        if (response.isSuccessful) {
                            if (validateResponse(response)) {
                                loading.value = false
                                responsePoliticas.postValue(response.body())
                                theJob.complete()
                            } else {
                                val error = "Error login"
                                onError(error)
                            }
                        } else {
                            val msg = response.message()
                            onError(msg)
                        }
                    }
                } catch (t: Throwable) {
                    val msg = t.message.toString()
                    onError(msg)
                }
            }
        }
    }

    fun categorias() {
        loading.value = true
        job = Job()
        job.let { theJob ->
            CoroutineScope(Dispatchers.IO + theJob!! + exceptionHandler).launch {
                try {
                    val response = MainRepository().categorias()
                    withContext(Main) {
                        if (response.isSuccessful) {
                            if (validateResponse(response)) {
                                loading.value = false
                                responseCategorias.postValue(response.body())
                                theJob.complete()
                            } else {
                                val error = "Error login"
                                onError(error)
                            }
                        } else {
                            val msg = response.message()
                            onError(msg)
                        }
                    }
                } catch (t: Throwable) {
                    val msg = t.message.toString()
                    onError(msg)
                }
            }
        }
    }

    fun loginv2(credentials: User) {
        loading.value = true
        job = Job()
        job.let { theJob ->
            CoroutineScope(Dispatchers.IO + theJob!! + exceptionHandler).launch {
                try {
                    val response = MainRepository().loginv2(credentials)
                    withContext(Main) {
                        if (response.isSuccessful) {
                            if (validateResponse(response)) {
                                loading.value = false
                                responseLoginAPIv2.postValue(response.body())
                                theJob.complete()
                            } else {
                                val error = "Error login"
                                onError(error)
                            }
                        } else {
                            val msg = response.message()
                            onError(msg)
                        }
                    }
                } catch (t: Throwable) {
                    val msg = t.message.toString()
                    onError(msg)
                }
            }
        }
    }

    fun especialidadDelDia() {
        loading.value = true
        job = Job()
        job.let { theJob ->
            CoroutineScope(Dispatchers.IO + theJob!! + exceptionHandler).launch {
                try {
                    val response = MainRepository().especialidadDelDia()
                    withContext(Main) {
                        if (response.isSuccessful) {
                            if (validateResponse(response)) {
                                loading.value = false
                                responseEspecialidadDia.postValue(response.body())
                                theJob.complete()
                            } else {
                                val error = "Error login"
                                onError(error)
                            }
                        } else {
                            val msg = response.message()
                            onError(msg)
                        }
                    }
                } catch (t: Throwable) {
                    val msg = t.message.toString()
                    onError(msg)
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

    fun logo() {

    }

}
