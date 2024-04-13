package com.example.compras.dialog

interface CustomDialogListener {
    fun adicionarItem(nome: String, quantidade: Int, valor: Double)
}