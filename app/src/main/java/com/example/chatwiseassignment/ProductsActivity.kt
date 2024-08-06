package com.example.chatwiseassignment

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwiseassignment.fragments.ProductDetailsFragment
import com.example.chatwiseassignment.interfaces.onProductClickListener
import com.example.chatwiseassignment.models.Product
import com.example.chatwiseassignment.models.ProductsResponse
import com.example.chatwiseassignment.recyclerView.ProductsListAdapter
import com.example.chatwiseassignment.retrofit.ProductsApi
import com.example.chatwiseassignment.retrofit.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductsActivity : AppCompatActivity(),onProductClickListener {

    private lateinit var adapter : ProductsListAdapter
    private val items = mutableListOf<Product>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_products_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductsListAdapter(items,this)
        recyclerView.adapter = adapter

        val productsApi = RetrofitHelper.getInstance().create(ProductsApi::class.java)
        lifecycleScope.launch {
            try {
                val response: Response<ProductsResponse> = productsApi.getProducts()
                if (response.isSuccessful) {
                    val products = response.body()?.products

                    products?.let { items.addAll(products) }
                    adapter.notifyDataSetChanged()
                    Log.d("Retrofit Fetch: ", "$products")
                } else {
                    Toast.makeText(this@ProductsActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@ProductsActivity, "Failed to fetch data: ${e.message}", Toast.LENGTH_SHORT).show()
                Log.e("ERROR","${e.message}")
            }
        }
    }
    override fun onProductClick(product: Product){
        Log.d("ProductsActivity","onProductClick arrived")
        val fragment = ProductDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("product", product) // Pass the product data
            }
        }
        Log.d("ProductsActivity","Replacing fragment")
        supportFragmentManager.beginTransaction()
            .replace(R.id.products_activity, fragment) // Replace the fragment in the container
            .addToBackStack(null) // Optional: Add to back stack to handle back navigation
            .commit()
        Log.d("ProductsActivity","Fragment transaction committed")
    }

}