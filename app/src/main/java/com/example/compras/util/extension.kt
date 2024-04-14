package com.example.compras.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.text.NumberFormat
import java.util.Locale

fun EditText.addCurrencyTextWatcher() {
    val editText = this
    editText.addTextChangedListener(object : TextWatcher {
        private var current = ""

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            if (s.toString() != current) {
                editText.removeTextChangedListener(this)

                val cleanString = s.toString().replace(Regex("[^0-9]"), "") // Remove todos os caracteres não numéricos
                val parsed = cleanString.toDoubleOrNull() ?: 0.0
                val formatted = formatCurrency(parsed / 100)

                current = formatted
                editText.setText(formatted)
                editText.setSelection(formatted.length)

                editText.addTextChangedListener(this)
            }
        }

        fun formatCurrency(value: Double): String {
            val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            format.minimumFractionDigits = 2
            return format.format(value)
        }
    })
}

fun EditText.removeCurrencyMask() {
    val editText = this
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            editText.removeTextChangedListener(this)

            // Remove todos os caracteres não numéricos
            val cleanString = s.toString().replace(Regex("[^0-9.]"), "")
            editText.setText(cleanString)

            editText.addTextChangedListener(this)
        }
    })
}

fun Double.formatAssCurrency(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    format.minimumFractionDigits = 2
    return format.format(this)
}
