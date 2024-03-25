package com.example.compras.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.compras.R
import com.example.compras.dataBase.SharedPreferences
import com.example.compras.databinding.ActivitySplashBinding
import com.example.compras.init.InitActivity
import com.example.compras.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupAnimationAndNavigate()
    }

    private fun setupAnimationAndNavigate() {
        lifecycleScope.launch {
            var count = 0f
            for (i in 1..50) {
                binding.logo.alpha = count
                count += 0.02f
                delay(25)
            }
            navigate()
        }
    }

    private fun navigate() {
        val userName = SharedPreferences.getName(this)
        val intent = if (userName != null) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, InitActivity::class.java)
        }
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
