package com.example.cash_register

import android.content.Context
import android.content.Context.MODE_PRIVATE

object Prefs {
    fun setBoolean(context: Context, sharedPrefs: String, key: String, value: Boolean?) {
        val sp = context.getSharedPreferences(sharedPrefs, MODE_PRIVATE)
        val editor = sp.edit()

        editor.putBoolean(key, value!!)
        editor.apply()
    }

    fun setString(context: Context, sharedPrefs: String, key: String, value: String) {
        val sp = context.getSharedPreferences(sharedPrefs, MODE_PRIVATE)
        val editor = sp.edit()

        editor.putString(key, value)
        editor.apply()
    }

    fun getBoolean(context: Context, sharedPrefs: String, key: String): Boolean {
        val sharedPreferences = context.getSharedPreferences(sharedPrefs, MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, false)
    }

    fun getString(context: Context, sharedPrefs: String, key: String): String? {
        val sharedPreferences = context.getSharedPreferences(sharedPrefs, MODE_PRIVATE)
        return sharedPreferences.getString(key, "")
    }
}