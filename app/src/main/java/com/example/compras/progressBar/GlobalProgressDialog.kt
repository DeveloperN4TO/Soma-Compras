package com.example.compras.progressBar

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.example.compras.R

class GlobalProgressDialog(context: Context) : AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_progress)
        setCancelable(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
}
