package com.example.compras.init

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.compras.R
import com.example.compras.databinding.ActivityInitBinding
import com.example.compras.main.MainActivity

class InitActivity : AppCompatActivity() {
    private val binding get() = _binding!!
    private var _binding: ActivityInitBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onClick()
        onValidate()

    }

    private fun onClick() {
        with(binding){
            btnNext.setOnClickListener {
                val intent = Intent(this@InitActivity, MainActivity::class.java)
                startActivity(intent)

            }

        }


    }

    private fun onValidate(): Boolean{

        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}