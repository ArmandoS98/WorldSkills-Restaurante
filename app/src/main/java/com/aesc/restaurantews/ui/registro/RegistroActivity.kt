package com.aesc.restaurantews.ui.registro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.aesc.restaurantews.R
import com.aesc.restaurantews.Util.Utils
import com.aesc.restaurantews.provider.services.models.Registro
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

        })

        viewModels.errorMessage.observe(this, {
            Utils.logsUtils("ERROR $it")
        })

        viewModels.loading.observe(this, {
            if (it) {
                Utils.logsUtils("SHOW")
            } else {
                Utils.logsUtils("HIDE")
            }
        })

        //Crear el cliente
        val user = Registro("Francisco Santos", "Santa Rosa", "c@cg1.com", "2021")
        viewModels.registrar(user)
    }
}