package com.example.compras.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.compras.dataClass.Product
import com.example.compras.databinding.ItemProductBinding
import com.example.compras.util.formatAssCurrency

class ProductAdapter(
    private val productList: MutableList<Product>,
    private val productDeleteListener: ProductDeleteListener? = null // Opcional agora
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    interface ProductDeleteListener {
        fun onDeleteProductClicked(position: Int)
    }

    fun updateData(newProducts: List<Product>) {
        productList.clear()
        productList.addAll(newProducts)
        notifyDataSetChanged()
    }

    class ProductViewHolder(
        private val binding: ItemProductBinding,
        private val productDeleteListener: ProductDeleteListener?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product, position: Int) {
            binding.apply {
                productName.text = product.nome
                productQuant.text = product.quantidade.toString()
                productValue.text = product.valor.formatAssCurrency()

                productDelete.setOnClickListener {
                    productDeleteListener?.onDeleteProductClicked(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding, productDeleteListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product, position)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}

