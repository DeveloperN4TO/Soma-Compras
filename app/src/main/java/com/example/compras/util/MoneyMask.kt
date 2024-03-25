package com.example.compras.util

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import java.lang.ref.WeakReference
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class MoneyMask(editText: TextInputEditText, private val action: (String) -> Unit = {}) : TextWatcher {

    private val mText: WeakReference<TextInputEditText> = WeakReference(editText)
    private val mFormatter: NumberFormat = NumberFormat.getCurrencyInstance(LOCALE_PT_BR)
    var mIsUpdating: Boolean = false

    override fun afterTextChanged(s: Editable) = Unit

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        mText.get()?.setSelection(mText.get()?.text?.length ?: 0)
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        var aux = s
        if (mIsUpdating) {
            mIsUpdating = false
            return
        }
        mIsUpdating = true
        val str = unmask(aux.toString())

        try {
            val parsed = java.lang.Double.parseDouble(str)
            if (parsed == 0.toDouble()) {
                mText.get()?.text = null
                return
            }
            aux = mFormatter.format(parsed / 100)
            if (mText.get()?.text?.toString()?.contentEquals(aux) == false) {
                val v = String.format(LOCALE_PT_BR, "R$ %.2f", parsed / 100)
                action(v)
                mText.get()?.setText(v)
            }

        } catch (e: NumberFormatException) {
            Unit
        }

        mText.get()?.setSelection(mText.get()?.text?.length ?: 0)
    }

    companion object {
        private val LOCALE_PT_BR = Locale("pt", "BR")

        fun getBRFormatter(currencyView: TextInputEditText, action: (String) -> Unit = {}): MoneyMask {
            val mask = MoneyMask(currencyView, action)
            val formatter = mask.mFormatter as DecimalFormat
            val symbols = formatter.decimalFormatSymbols
            symbols.currencySymbol = ""
            formatter.decimalFormatSymbols = symbols
            return mask
        }

        fun unmask(masked: String?) = masked?.replace("[^\\d]".toRegex(), "") ?: ""

        fun doubleValue(masked: String): Double = try {
            java.lang.Double.parseDouble(unmask(masked)) / 100
        } catch (ignore: NumberFormatException) {
            0.0
        }
    }
}
