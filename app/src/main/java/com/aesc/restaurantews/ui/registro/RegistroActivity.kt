package com.aesc.restaurantews.ui.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.aesc.restaurantews.R
import com.aesc.restaurantews.Util.Utils
import com.aesc.restaurantews.extensions.goToActivityF
import com.aesc.restaurantews.extensions.toast
import com.aesc.restaurantews.provider.Preferences.PreferencesKey
import com.aesc.restaurantews.provider.Preferences.PreferencesProvider
import com.aesc.restaurantews.provider.services.models.Registro
import com.aesc.restaurantews.provider.services.models.User
import com.aesc.restaurantews.ui.home.MainActivity
import com.aesc.restaurantews.ui.login.LoginActivity
import com.aesc.visaappk.provider.services.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.activity_registro.tiePassword
import kotlinx.android.synthetic.main.activity_registro.tieUserName

class RegistroActivity : AppCompatActivity(), View.OnClickListener {
    //ingresando su nombre, ciudad, un correo y una contraseña.
    lateinit var viewModels: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        viewModels = ViewModelProvider(this).get(MainViewModel::class.java)

        btnRegistrar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        getPolitics()
    }

    private fun getPolitics() {
        viewModels.responsePoliticas.observe(this, {
            Utils.logsUtils("SUCCESS $it")
            val pDialog = SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
            pDialog.titleText = "Politicas de Privacidad"
            pDialog.contentText = it.datos!!.politicas
            pDialog.setCancelable(false)
            pDialog.cancelText = "CANCEL"
            pDialog.confirmText = "OK"
            pDialog.setCancelClickListener {
                toast("La cuenta no fue creada", Toast.LENGTH_LONG)
                pDialog.dismiss()
            }
            pDialog.setConfirmClickListener {
                createAccount()
            }
            pDialog.show()
        })

        viewModels.errorMessage.observe(this, {
            toast(it, Toast.LENGTH_LONG)
        })

        viewModels.loading.observe(this, {
            if (it) {
                Utils.logsUtils("SHOW")
            } else {
                Utils.logsUtils("HIDE")
            }
        })

        viewModels.politicasPrivacidad()
    }

    private fun createAccount() {
        val name = tieUserName.text.toString()
        val city = tieCiudad.text.toString()
        val mail = tieCorreo.text.toString()
        val password = tiePassword.text.toString()

        viewModels.responseAPI.observe(this, {
            Utils.logsUtils("SUCCESS $it")
            toast(it.menssage, Toast.LENGTH_LONG)
            login(mail, password)
        })

        viewModels.errorMessage.observe(this, {
            toast(it, Toast.LENGTH_LONG)
        })

        viewModels.loading.observe(this, {
            if (it) {
                Utils.logsUtils("SHOW")
            } else {
                Utils.logsUtils("HIDE")
            }
        })


        //Crear el cliente
        val user = Registro(name, mail, password, city)
        viewModels.registrar(user)
    }

    private fun login(user: String, password: String) {
        viewModels.responseLoginAPIv2.observe(this, {
            Utils.logsUtils("SUCCESS $it")
            toast("LOGIN SUCCESS", Toast.LENGTH_LONG)
            PreferencesProvider.set(this, PreferencesKey.AUTH_USER, true)
            PreferencesProvider.set(this, PreferencesKey.ID_USER, it.idCliente!!)
            PreferencesProvider.set(this, PreferencesKey.NAME_USER, it.nombre!!)
            PreferencesProvider.set(this, PreferencesKey.TOKEN_USER, it.token!!)
            goToActivityF<MainActivity>()
        })

        viewModels.errorMessage.observe(this, {
            Utils.logsUtils("ERROR $it")
            toast(it, Toast.LENGTH_LONG)
        })

        viewModels.loading.observe(this, {
            if (it) {
                Utils.logsUtils("SHOW")
            } else {
                Utils.logsUtils("HIDE")
            }
        })

        //Crear el cliente
        val user = User(user, password)
        viewModels.loginv2(user)
    }
}