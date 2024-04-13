package com.example.compras.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.compras.dataBase.SharedPreferences
import com.example.compras.databinding.FragmentListBinding
import com.example.compras.dialog.CustomDialog


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initGetShared()
        initCurrent()
        onClick()

        val dialog = CustomDialog(
            requireContext()
        )

    }

    private fun initCurrent() {
        SharedPreferences.saveCurrent(requireContext(),
            binding.saldo.text.toString())
    }

    private fun initGetShared() {
        val userName = SharedPreferences.getName(requireContext())
        if (!userName.isNullOrEmpty()) {

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

    private fun onClick() = with(binding){
        addNewItem.setOnClickListener {
            val dialog = CustomDialog(
                requireContext()
            )
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

}