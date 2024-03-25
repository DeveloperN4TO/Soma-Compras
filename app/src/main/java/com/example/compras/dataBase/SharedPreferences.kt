package com.example.compras.dataBase

import android.content.Context

class SharedPreferences {
    companion object {
        private const val PREF_NAME = "MyPreferences"
        private const val PREF_CURRENT = "current"

        fun saveName(context: Context, name: String) {
            val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("userName", name)
            editor.apply()
        }

        fun getName(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getString("userName", null)
        }

        fun saveCurrent(context: Context, current: String){
            val sharedPreferences = context.getSharedPreferences(PREF_CURRENT, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("current", current)
            editor.apply()
        }

        fun getCurrent(context: Context): String? {
            val sharedPreferences = context.getSharedPreferences(PREF_CURRENT, Context.MODE_PRIVATE)
            return sharedPreferences.getString("current", null)
        }
    }
}