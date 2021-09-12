package com.aesc.restaurantews.ui.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.aesc.restaurantews.R
import com.aesc.restaurantews.Util.Utils
import com.aesc.restaurantews.extensions.goToActivityF
import com.aesc.restaurantews.extensions.toast
import com.aesc.restaurantews.provider.services.models.Registro
import com.aesc.restaurantews.ui.login.LoginActivity
import com.aesc.visaappk.provider.services.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_registro.*

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
        viewModels.responseAPI.observe(this, {
            Utils.logsUtils("SUCCESS $it")
            toast(it.menssage, Toast.LENGTH_LONG)
            goToActivityF<LoginActivity>()
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

        val name = tieUserName.text.toString()
        val city = tieCiudad.text.toString()
        val mail = tieCorreo.text.toString()
        val password = tiePassword.text.toString()

        //Crear el cliente
        val user = Registro(name, mail, password, city)
        viewModels.registrar(user)
    }
}