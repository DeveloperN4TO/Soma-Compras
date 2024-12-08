package com.example.compras.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.compras.R
import com.example.compras.adapter.ProductAdapter
import com.example.compras.dataBase.SharedPreferences
import com.example.compras.dataClass.Product
import com.example.compras.databinding.FragmentListBinding
import com.example.compras.dialog.CustomDialog
import com.example.compras.dialog.CustomDialogListener
import com.example.compras.util.formatAssCurrency

class ListFragment : Fragment(), CustomDialogListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val productList = mutableListOf<Product>()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initGetShared()
        initCurrent()
        onClick()
    }

    private fun initRecyclerView() {
        productAdapter = ProductAdapter(productList, productDeleteListener =
        object : ProductAdapter.ProductDeleteListener {
            override fun onDeleteProductClicked(position: Int) {
                productList.removeAt(position)
                productAdapter.notifyItemRemoved(position)

                if (productAdapter.itemCount == 0){
                    binding.finishButton.visibility = View.GONE
                    binding.orderEmpty.visibility = View.VISIBLE
                    binding.totalValue.text = getString(R.string.default_price)
                }
            }
        })

        binding.allProducerHome.apply {
            layoutManager = LinearLayoutManager(requireContext())
            productAdapter.notifyDataSetChanged()
            adapter = productAdapter

        }
    }

    private fun initCurrent() {
        SharedPreferences.saveCurrent(requireContext(), binding.balance.text.toString())
    }

    private fun initGetShared() {
//        val userName = SharedPreferences.getName(requireContext())

        val currentBalance = SharedPreferences.getCurrent(requireContext())
        if (!currentBalance.isNullOrEmpty()) {
            binding.balance.setText(currentBalance)
        }

        binding.balance.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                SharedPreferences.saveCurrent(requireContext(), s.toString())
            }

        })
    }

    private fun onClick() = with(binding){
        addNewItem.setOnClickListener {
            val dialog = CustomDialog { nome, quantidade, valor ->
                addItem(nome, quantidade, valor)
            }
            dialog.show(requireActivity().supportFragmentManager, "CustomDialog")
        }

        btnHistoric.setOnClickListener {
            findNavController().navigate(ListFragmentDirections.actionListFragmentToHistoricFragment())
        }
    }

    override fun addItem(nome: String, quantidade: Int, valor: Double) {
        val novoProduto = Product(nome, quantidade, valor)
        productList.add(novoProduto)
        productAdapter.notifyDataSetChanged()

        var valorTotal = 0.0
        for (produto in productList) {
            valorTotal += produto.quantidade * produto.valor
        }

        binding.totalValue.text = valorTotal.formatAssCurrency()

        if (productAdapter.itemCount > 0){
            binding.finishButton.visibility = View.VISIBLE
            binding.orderEmpty.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
