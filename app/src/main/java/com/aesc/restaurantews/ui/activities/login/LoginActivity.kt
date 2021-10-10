package com.aesc.restaurantews.ui.activities.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cn.pedant.SweetAlert.SweetAlertDialog
import com.aesc.restaurantews.R
import com.aesc.restaurantews.Util.Utils
import com.aesc.restaurantews.extensions.goToActivity
import com.aesc.restaurantews.extensions.goToActivityF
import com.aesc.restaurantews.extensions.toast
import com.aesc.restaurantews.provider.Preferences.PreferencesKey
import com.aesc.restaurantews.provider.Preferences.PreferencesProvider
import com.aesc.restaurantews.provider.services.models.User
import com.aesc.restaurantews.ui.activities.home.MainActivity
import com.aesc.restaurantews.ui.activities.registro.RegistroActivity
import com.aesc.visaappk.provider.services.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var viewModels: MainViewModel
    lateinit var pDialog: SweetAlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModels = ViewModelProvider(this).get(MainViewModel::class.java)

        initComponent()
    }

    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.tvSignup -> {
                goToActivity<RegistroActivity>()
            }
            R.id.tvForgotPassword -> {
            }
            R.id.btn_login -> {
                login()
            }
        }
    }

    private fun initComponent() {
        tieUserName.setText("c@cg3.com")
        tiePassword.setText("2021")

        btn_login.setOnClickListener(this)
        tvSignup.setOnClickListener(this)
        tvForgotPassword.setOnClickListener(this)

        shotBottom()
    }

    private fun shotBottom() {
//        val dialog = BottomSheetDialog(this)

    }

    private fun login() {
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

        val userText = tieUserName.text.toString()
        val passText = tiePassword.text.toString()

        //Crear el cliente
        val user = User(userText, passText)
        viewModels.loginv2(user)
    }

    private fun showError(body: String?) {
        pDialog = SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
        pDialog.titleText = "Error"
        pDialog.contentText = body
        pDialog.setCancelable(false)
        pDialog.show()
    }
}