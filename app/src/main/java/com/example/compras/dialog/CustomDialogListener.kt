package com.example.compras.dialog

interface CustomDialogListener {
    fun addItem(nome: String, quantidade: Int, valor: Double)
}