package com.example.check24app.ui.adapters.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.check24app.databinding.ProductItemBinding
import com.example.check24app.model.Product
import com.example.check24app.model.Products

class ProductsRecyclerAdapter(private val itemClickListener: onItemClick) :
    RecyclerView.Adapter<ProductsRecyclerAdapter.ProductViewHolder>() {

    private var products = emptyList<Product>()


    class ProductViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(response: Product, listener: onItemClick) {
            binding.product = response
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                listener.itemClicked(response)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProductItemBinding.inflate(layoutInflater, parent, false)
                return ProductViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position], itemClickListener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun setData(foodRecipe: Products) {
        val diffUtil = RecipesDiffUtil(products, foodRecipe.products)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        products = foodRecipe.products
        diffUtilResult.dispatchUpdatesTo(this)
    }

    interface onItemClick {
        fun itemClicked(item: Product)
    }


    class RecipesDiffUtil(
        private val oldResult: List<Product>,
        private val newResult: List<Product>
    ) : DiffUtil.Callback() {


        override fun getOldListSize(): Int {
            return oldResult.size
        }

        override fun getNewListSize(): Int {
            return newResult.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldResult[oldItemPosition] === newResult[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldResult[oldItemPosition] == newResult[newItemPosition]
        }
    }
}