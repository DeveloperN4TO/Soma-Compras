package com.example.compras.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import com.example.compras.R

class CustomDialog(context: Context) : Dialog(context) {

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.custom_add_product, null)
        setContentView(view)

        // Inicializar as referências aos EditTexts
        editText1 = view.findViewById(R.id.editText1)
        editText2 = view.findViewById(R.id.editText2)
        editText3 = view.findViewById(R.id.editText3)
    }

    // Métodos para obter o texto dos EditTexts
    fun getText1(): String {
        return editText1.text.toString()
    }

    fun getText2(): String {
        return editText2.text.toString()
    }

    fun getText3(): String {
        return editText3.text.toString()
    }
}
