package com.example.compras.ui.historic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.example.compras.adapter.ProductAdapter
import com.example.compras.dataBase.SharedPreferences
import com.example.compras.dataClass.Product
import com.example.compras.databinding.FragmentHistoricBinding

class HistoricFragment : Fragment() {

    private var _binding: FragmentHistoricBinding? = null
    private val binding get() = _binding!!
    private val historicList = mutableListOf<Product>()
    private val viewModel: HistoricViewModel by viewModels() // Instancia o ViewModel
    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoricBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        loadHistoricData()
        onClick()
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(mutableListOf())
        binding.historicRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
    }

    private fun setupObservers() {
        viewModel.historicalData.observe(viewLifecycleOwner, Observer { purchases ->

            productAdapter.updateData(purchases)

            if (purchases.isNullOrEmpty()) {
                binding.orderEmpty.visibility = View.VISIBLE
                binding.historicRecyclerView.visibility = View.GONE
            } else {
                binding.orderEmpty.visibility = View.GONE
                binding.historicRecyclerView.visibility = View.VISIBLE
            }
        })
    }

    private fun loadHistoricData() {
        viewModel.loadPurchases(requireContext())
        val savedPurchases = SharedPreferences.getPurchaseHistory(requireContext()) ?: emptyList()

        if (savedPurchases.isEmpty()) {
        }

        historicList.clear()
        historicList.addAll(savedPurchases)

        productAdapter.notifyDataSetChanged()

        if (historicList.isEmpty()) {
            binding.orderEmpty.visibility = View.VISIBLE
            binding.historicRecyclerView.visibility = View.GONE
        } else {
            binding.orderEmpty.visibility = View.GONE
            binding.historicRecyclerView.visibility = View.VISIBLE
        }
    }

    private fun onClick() = with(binding) {
        toolBar.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
