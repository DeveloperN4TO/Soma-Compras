package com.example.compras.ui.historic

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var productAdapter: ProductAdapter
    private lateinit var viewModel: HistoricViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoricBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(HistoricViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadHistoricData()
        onClick()

        // Observando as mudanças no LiveData
        viewModel.historicalData.observe(viewLifecycleOwner) { savedPurchases ->
            Log.d("AASF", "OBSERVER: $savedPurchases")

            historicList.clear()
            historicList.addAll(savedPurchases)
            productAdapter.notifyDataSetChanged()

            // Ajusta a visibilidade dependendo do conteúdo da lista
            if (historicList.isEmpty()) {
                binding.orderEmpty.visibility = View.VISIBLE
                binding.historicRecyclerView.visibility = View.GONE
            } else {
                binding.orderEmpty.visibility = View.GONE
                binding.historicRecyclerView.visibility = View.VISIBLE
            }
        }

    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(historicList)
        Log.d("AASF", "SETUP RECYCLER: $historicList")

        binding.historicRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
    }


    private fun loadHistoricData() {
        // Tenta carregar o histórico salvo no SharedPreferences
//        val savedPurchases = SharedPreferences.getPurchaseHistory(requireContext()) ?: emptyList()

        // Log para verificar o que foi recuperado
//        Log.d("AASF", "LOAD HISTORIC: $savedPurchases")
//
//        if (savedPurchases.isEmpty()) {
//            Log.d("AASF", "No purchases found in SharedPreferences.")
//        }

        // Atualiza a lista e notifica o adapter
        historicList.clear()
//        historicList.addAll(savedPurchases)

        // Log para verificar o estado da lista após adicionar os itens
        Log.d("AASF", "LOAD HISTORIC 2: $historicList")

        productAdapter.notifyDataSetChanged()

        // Ajusta a visibilidade dependendo do conteúdo da lista
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
