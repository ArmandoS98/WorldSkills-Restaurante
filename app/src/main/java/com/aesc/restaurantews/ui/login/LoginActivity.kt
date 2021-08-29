package com.aesc.restaurantews.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.aesc.restaurantews.R
import com.aesc.restaurantews.Util.Utils.gotoDestinations
import com.aesc.restaurantews.ui.registro.RegistroActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tvRegistro.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val id = v?.id
        when (id) {
            R.id.tvRegistro -> {
                gotoRegister()
            }
        }
    }

    private fun gotoRegister() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
        finish()
    }
}