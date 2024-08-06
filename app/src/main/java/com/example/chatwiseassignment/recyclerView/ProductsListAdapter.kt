package com.example.chatwiseassignment.recyclerView

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwiseassignment.R
import com.example.chatwiseassignment.fragments.ProductDetailsFragment
import com.example.chatwiseassignment.interfaces.onProductClickListener
import com.example.chatwiseassignment.models.Product
import com.squareup.picasso.Picasso


class ProductsListAdapter(private val items: List<Product>,private val clickListener:onProductClickListener) : RecyclerView.Adapter<ProductsListAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)

    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {

        val product = items[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            Log.d("ProductsListAdapter","Product clicked")
            clickListener.onProductClick(product)
        }

    }

    override fun getItemCount(): Int = items.size



    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.title)
        private val price: TextView = itemView.findViewById(R.id.price)
        private val rating: TextView = itemView.findViewById(R.id.rating)
        private val thumbnail: ImageView = itemView.findViewById(R.id.thumbnail)

        fun bind(product: Product) {
            title.text = product.title
            price.text = String.format("$%.2f", product.price)
            rating.text = product.rating.toString()
            Picasso.get().load(product.thumbnail).into(thumbnail)
        }
    }
}
