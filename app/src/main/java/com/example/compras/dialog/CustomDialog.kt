package com.example.compras.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.compras.databinding.CustomAddProductBinding

class CustomDialog(private val callback: (String, Int, Double) -> Unit) : AppCompatDialogFragment() {

    private lateinit var binding: CustomAddProductBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = CustomAddProductBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()

        binding.buttonAddProduct.setOnClickListener {
            val nome = binding.nameProduct.text.toString()
            val quantidade = binding.quantityProduct.text.toString().toInt()
            val valor = binding.valueProduct.text.toString().toDouble()
            callback.invoke(nome, quantidade, valor)
            dialog.dismiss()
        }

        binding.buttonCancelProduct.setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }
}
