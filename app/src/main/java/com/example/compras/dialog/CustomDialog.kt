package com.example.compras.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.compras.databinding.CustomAddProductBinding
import com.example.compras.util.addCurrencyTextWatcher
import com.example.compras.util.removeCurrencyMask

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
            val valorString = binding.valueProduct.text.toString()

            val cleanString = valorString.replace(Regex("[^0-9.]"), "")
            val valorNumerico = cleanString.toDoubleOrNull() ?: 0.0

            callback.invoke(nome, quantidade, valorNumerico)
            dialog.dismiss()
        }

        binding.buttonCancelProduct.setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }
}
