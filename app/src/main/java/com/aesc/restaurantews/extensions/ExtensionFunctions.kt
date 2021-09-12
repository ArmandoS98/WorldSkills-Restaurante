package com.aesc.restaurantews.extensions

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Activity.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

fun Activity.toast(resourceId: Int, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, resourceId, duration).show()

fun ViewGroup.inflate(layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)!!

fun ImageView.loadByURL(url: String) = Glide.with(this).load(url).into(this)

fun ImageView.loadByResource(resource: Int) = Glide.with(this).load(resource).into(this)

inline fun <reified T : Activity> Activity.goToActivityF(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
    finish()
}

inline fun <reified T : Activity> Activity.goToActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}
