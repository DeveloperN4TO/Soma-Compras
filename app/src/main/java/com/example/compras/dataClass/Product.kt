package com.example.compras.dataClass

import java.io.Serializable

data class Product(
    val nome: String,
    val quantidade: Int,
    val valor: Double
) : Serializable