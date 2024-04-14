package com.example.compras.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.compras.dataClass.Product
import com.example.compras.databinding.ItemProductBinding
import com.example.compras.util.formatAssCurrency

class ProductAdapter(private val productList: MutableList<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.apply {
                productName.text = product.nome
                productQuant.text = product.quantidade.toString()

                Log.e("teste", product.quantidade.toString())
                Log.e("teste", product.valor.toString())
                Log.e("teste", product.nome.toString())

                // Formatando o valor como moeda brasileira
                productValue.text = product.valor.formatAssCurrency()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}
