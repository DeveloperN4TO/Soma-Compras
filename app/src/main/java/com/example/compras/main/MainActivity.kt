package com.example.compras.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.compras.R
import com.example.compras.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }
        supportActionBar?.hide()

        navHostFragment = supportFragmentManager.findFragmentById(R.id.containerPai) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.navigationMenu.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _ , destination, _ ->
            when(destination.id){
                R.id.homeFragment -> binding.navigationMenu.visibility = View.VISIBLE
                R.id.listFragment -> binding.navigationMenu.visibility = View.VISIBLE
                R.id.historicFragment -> binding.navigationMenu.visibility = View.VISIBLE
                else -> binding.navigationMenu.visibility = View.GONE
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}