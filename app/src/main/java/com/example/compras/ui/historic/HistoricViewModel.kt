package com.example.compras.ui.historic

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.compras.dataBase.SharedPreferences
import com.example.compras.dataClass.Product

class HistoricViewModel : ViewModel() {
    private val _historicalData = MutableLiveData<List<Product>>()
    val historicalData: LiveData<List<Product>> get() = _historicalData

//    fun loadPurchases(context: Context) {
//        _historicalData.value = SharedPreferences.getPurchaseHistory(context)
//    }
}
