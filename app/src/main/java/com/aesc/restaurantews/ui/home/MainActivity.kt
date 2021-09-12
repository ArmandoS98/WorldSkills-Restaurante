package com.aesc.restaurantews.ui.home

import android.os.Bundle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.aesc.restaurantews.R
import com.aesc.restaurantews.Util.Utils
import com.aesc.restaurantews.databinding.ActivityMainBinding
import com.aesc.restaurantews.extensions.goToActivityF
import com.aesc.restaurantews.provider.Preferences.PreferencesKey
import com.aesc.restaurantews.provider.Preferences.PreferencesProvider
import com.aesc.restaurantews.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_menu, R.id.nav_pedido, R.id.nav_perfil
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        userAuth()

        listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                when (destination.id) {
                }
            }
    }

    private fun userAuth() {
        val auth = PreferencesProvider.bool(this, PreferencesKey.AUTH_USER)
        val id = PreferencesProvider.string(this, PreferencesKey.ID_USER)
        val token = PreferencesProvider.string(this, PreferencesKey.TOKEN_USER)
        val name = PreferencesProvider.string(this, PreferencesKey.NAME_USER)

        Utils.logsUtils("id:$id  token:$token  name:$name")

        if (!auth!!) {
            goToActivityF<LoginActivity>()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()
        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()
        navController.removeOnDestinationChangedListener(listener)
    }
}