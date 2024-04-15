package com.example.compras.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.compras.dataBase.SharedPreferences
import com.example.compras.databinding.FragmentHomeBinding
import com.example.compras.util.addCurrencyTextWatcher

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
        initGetShared()
        initCurrent()

        // Adiciona o TextWatcher para formatar o saldo como moeda enquanto o usuário digita
        binding.saldo.addCurrencyTextWatcher()
    }

    private fun initCurrent() {
        SharedPreferences.saveCurrent(requireContext(), binding.saldo.text.toString())
    }

    private fun initGetShared() {
        val userName = SharedPreferences.getName(requireContext())
        if (!userName.isNullOrEmpty()) {
            binding.txtNameUser.text = "$userName!"
        }

        val currentBalance = SharedPreferences.getCurrent(requireContext())
        if (!currentBalance.isNullOrEmpty()) {
            binding.saldo.setText(currentBalance)
        }

        binding.saldo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                SharedPreferences.saveCurrent(requireContext(), s.toString())
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
