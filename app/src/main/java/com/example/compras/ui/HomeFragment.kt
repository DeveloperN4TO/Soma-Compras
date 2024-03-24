package com.example.compras.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.clearFragmentResult
import com.example.compras.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        onClick()
        setupExitToApp()

    }

    private fun initAdapter() {

    }

    private fun onClick() {
        with(binding){


        }

    }

    private fun setupExitToApp() {
        binding.btnExit.setOnClickListener {
            finishAffinity(
                requireActivity()
            )


        }
        binding.btnExit.setOnLongClickListener {
            finishAffinity(
                requireActivity()
            )
            activity?.finish()
            true
        }

        binding.txtExit.setOnClickListener {
            finishAffinity(
                requireActivity()
            )
            activity?.finish()

        }
        binding.txtExit.setOnLongClickListener {
            finishAffinity(
                requireActivity()
            )
            activity?.finish()
            true
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}