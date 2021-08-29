package com.aesc.restaurantews.provider.Preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


enum class PreferencesKey(val value: String) {
    AUTH_USER("authUser"),
    TOKEN_USER("tokenUser"),
    USER_LOGIN("userLogin")
}

object PreferencesProvider {

    fun set(context: Context, key: PreferencesKey, value: String) {
        val editor = prefs(context).edit()
        editor.putString(key.value, value).apply()
    }

    fun string(context: Context, key: PreferencesKey): String? {
        return prefs(context).getString(key.value, null)
    }

    fun set(context: Context, key: PreferencesKey, value: Boolean) {
        val editor = prefs(context).edit()
        editor.putBoolean(key.value, value).apply()
    }

    fun bool(context: Context, key: PreferencesKey): Boolean? {
        return prefs(context).getBoolean(key.value, false)
    }

    fun set(context: Context, key: PreferencesKey, value: Any) {
        val editor = prefs(context).edit()
        val gson = Gson()
        val json = gson.toJson(value)
        editor.putString(key.value, json).apply()
    }


    fun getHashMapStrings(context: Context, key: PreferencesKey): HashMap<String, String> {
        val map = getHashMapS(context, key)
        var mapResult: HashMap<String, String> = HashMap()
        for ((key, value) in map) {
            mapResult[key] = value as String
        }
        return mapResult
    }

    private fun getHashMapS(context: Context, key: PreferencesKey): HashMap<String, Any> {
        val gson = Gson()
        val json = prefs(context).getString(key.value, null)
        val type = object : TypeToken<HashMap<String, Any>>() {}.type
        return gson.fromJson(json, type)
    }

    fun remove(context: Context, key: PreferencesKey) {
        val editor = prefs(context).edit()
        editor.remove(key.value).apply()
    }

    // Elimina las SharedPreferences del dominio app
    fun clear(context: Context) {
        val editor = prefs(context).edit()
        editor.clear().apply()
    }

    // Private
    private fun prefs(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}