package com.example.compras.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.navigation.fragment.findNavController
import com.example.compras.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        setupObserverPrivacy()
        setupExitApp()
        versionApp()
    }

    private fun setupObserverPrivacy() {

    }

    private fun versionApp(){

        val context = requireContext()
        val versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName

        binding.version.text = versionName

    }

    private fun setupClickListeners() = with(binding){
        btnHistoric.setOnClickListener {
           findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToHistoricFragment())

        }

    }

    private fun setupExitApp() {
        binding.btnExit.setOnClickListener {
            ActivityCompat.finishAffinity(
                requireActivity()
            )


        }
        binding.btnExit.setOnLongClickListener {
            ActivityCompat.finishAffinity(
                requireActivity()
            )
            activity?.finish()
            true
        }

        binding.container2.setOnClickListener {
            ActivityCompat.finishAffinity(
                requireActivity()
            )
            activity?.finish()

        }
        binding.icExit.setOnLongClickListener {
            ActivityCompat.finishAffinity(
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
