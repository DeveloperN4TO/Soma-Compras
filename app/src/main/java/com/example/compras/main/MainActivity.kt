package com.example.compras.main

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.compras.R
import com.example.compras.databinding.ActivityMainBinding
import com.example.compras.dialog.CustomDialog

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        navHostFragment = supportFragmentManager.findFragmentById(R.id.containerPai) as NavHostFragment
        navController = navHostFragment.findNavController()
        binding.navigationMenu.setupWithNavController(navController)
        binding.navigationMenu.itemIconSize = 110

        val dialog = CustomDialog(this)
        dialog.setOnDismissListener {
            val text1 = dialog.getText1()
            val text2 = dialog.getText2()
            val text3 = dialog.getText3()

        }

        binding.navigationMenu.findViewById<View>(R.id.listFragment).setOnClickListener {
            dialog.show()
            navController.navigate(R.id.homeFragment)
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.homeFragment, R.id.historicFragment -> {
                    binding.navigationMenu.visibility = View.VISIBLE
                }
                else -> {
                    binding.navigationMenu.visibility = View.GONE
                }

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
