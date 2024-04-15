package com.example.compras.init

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.compras.dataBase.SharedPreferences
import com.example.compras.databinding.ActivityInitBinding
import com.example.compras.main.MainActivity
import com.example.compras.progressBar.GlobalProgressDialog

class InitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitBinding
    private lateinit var progressDialog: GlobalProgressDialog // Mudança aqui

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClick()
    }

    private fun onClick() {
        binding.btnNext.setOnClickListener {
            val name = binding.editName.text.toString().trim()

            if (name.isEmpty()) {
                binding.editName.error = "Por favor, insira seu nome."
            } else {
                showProgressDialog()
                SharedPreferences.saveName(this, name)

                val handler = Handler(Looper.getMainLooper())
                handler.postDelayed({
                    dismissProgressDialog()
                    val intent = Intent(this@InitActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 2000L)
            }
        }
    }

    private fun showProgressDialog() {
        if (!::progressDialog.isInitialized) {
            progressDialog = GlobalProgressDialog(this)
            progressDialog.show()
        }
    }

    private fun dismissProgressDialog() {
        if (::progressDialog.isInitialized && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}
