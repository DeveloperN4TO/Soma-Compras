package com.example.compras.dataBase

import android.content.Context
import com.example.compras.dataClass.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferences {
    companion object {
        private const val PREF_NAME = "MyPreferences"
        private const val PREF_CURRENT = "current"
        private const val PREF_PURCHASE = "purchase"

        fun savePurchase(context: Context, purchase: List<Product>) {
            val sharedPreferences = context.getSharedPreferences(PREF_PURCHASE, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val gson = Gson()
            val json = gson.toJson(purchase) // Converte a lista de produtos para JSON
            editor.putString("listItem", json)
            editor.apply()
        }

        fun getPurchaseHistory(context: Context): List<Product>? {
            val sharedPreferences = context.getSharedPreferences(PREF_PURCHASE, Context.MODE_PRIVATE)
            val json = sharedPreferences.getString("listItem", null)

            if (json.isNullOrEmpty()) {
                return null
            }

            return try {
                val type = object : TypeToken<List<Product>>() {}.type
                val purchases: List<Product> = Gson().fromJson(json, type)

                purchases
            } catch (e: Exception) {
                null
            }
        }

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