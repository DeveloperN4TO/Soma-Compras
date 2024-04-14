package com.example.compras.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.compras.databinding.CustomAddProductBinding
import com.example.compras.util.addCurrencyTextWatcher

class CustomDialog(private val callback: (String, Int, Double) -> Unit) : AppCompatDialogFragment() {

    private lateinit var binding: CustomAddProductBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = CustomAddProductBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()
        binding.valueProduct.addCurrencyTextWatcher()

        binding.buttonAddProduct.setOnClickListener {
            val nome = binding.nameProduct.text.toString()
            val quantidade = binding.quantityProduct.text.toString().toInt()
            val valorText = binding.valueProduct.text.toString()

            val valorSemMascara = valorText.replace(Regex("[^0-9]"), "")

            val valorFormatado = if (valorSemMascara.takeLast(2) == "00") {
                valorSemMascara.dropLast(2)
            } else {
                (valorSemMascara.toDoubleOrNull() ?: 0.0) / 100
            }

            callback.invoke(nome, quantidade, valorFormatado.toString().toDouble())
            dialog.dismiss()
        }

        binding.buttonCancelProduct.setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }
}
