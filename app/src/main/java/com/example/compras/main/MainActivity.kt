package com.example.compras.main

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
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

        binding.navigationMenu.itemIconSize = 90

        binding.navigationMenu.setOnNavigationItemSelectedListener { item ->

            val animation = AnimationUtils.loadAnimation(this, R.anim.anim_bt)
            val view = binding.navigationMenu.findViewById<View>(item.itemId)
            view.startAnimation(animation)

            when (item.itemId) {
                R.id.homeFragment -> navController.navigate(R.id.homeFragment)
                R.id.listFragment -> navController.navigate(R.id.listFragment)
                R.id.historicFragment -> navController.navigate(R.id.historicFragment)
            }

            true
        }

        navController.addOnDestinationChangedListener { _ , destination, _ ->
            when(destination.id){
                R.id.homeFragment, R.id.listFragment, R.id.historicFragment -> binding.navigationMenu.visibility = View.VISIBLE
                else -> binding.navigationMenu.visibility = View.GONE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
