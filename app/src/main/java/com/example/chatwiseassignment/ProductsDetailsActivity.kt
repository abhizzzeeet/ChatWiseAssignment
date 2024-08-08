package com.example.chatwiseassignment

import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.chatwiseassignment.models.Product
import com.example.chatwiseassignment.recyclerView.ReviewAdapter

class ProductDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_details)

        val product = intent.getSerializableExtra("PRODUCT_DATA") as? Product

        product?.let {
            // Load product image using Glide or any other image loading library
            val productImage: ImageView = findViewById(R.id.productImage)
            Glide.with(this).load(it.thumbnail).into(productImage)

            // Set product details directly without using strings.xml
            findViewById<TextView>(R.id.productTitle).text = it.title
            findViewById<TextView>(R.id.productId).text = "Product ID: ${it.id}"
            findViewById<TextView>(R.id.productDescription).text = it.description
            findViewById<TextView>(R.id.productPrice).text = "Price: $${it.price}"
            findViewById<TextView>(R.id.productDiscountPercentage).text = "Discount: ${it.discountPercentage}%"
            findViewById<RatingBar>(R.id.productRating).rating = it.rating.toFloat()
            findViewById<TextView>(R.id.productRatingText).text = "${it.rating}"
            findViewById<TextView>(R.id.productBrand).text = "Brand: ${it.brand}"
            findViewById<TextView>(R.id.productWeight).text = "Weight: ${it.weight}"
            findViewById<TextView>(R.id.productDimensions).text = "Dimensions: ${it.dimensions.width} x ${it.dimensions.height} x ${it.dimensions.depth}"
            findViewById<TextView>(R.id.productWarrantyInformation).text = "Warranty: ${it.warrantyInformation}"
            findViewById<TextView>(R.id.productShippingInformation).text = "Shipping Info: ${it.shippingInformation}"
            findViewById<TextView>(R.id.productAvailabilityStatus).text = "Availability: ${it.availabilityStatus} (${it.stock} in stock)"
//            findViewById<TextView>(R.id.productReviews).text = it.reviews.joinToString("\n") { review -> "${review.reviewerName}: ${review.comment} (${review.rating} stars)" }
            findViewById<TextView>(R.id.productReturnPolicy).text = "Return Policy: ${it.returnPolicy}"
            findViewById<TextView>(R.id.productMinimumOrderQuantity).text = "Minimum Order Quantity: ${it.minimumOrderQuantity}"

            // Set up RecyclerView for reviews
            val recyclerViewReviews: RecyclerView = findViewById(R.id.recyclerViewReviews)
            recyclerViewReviews.layoutManager = LinearLayoutManager(this)
            recyclerViewReviews.adapter = ReviewAdapter(it.reviews)
        }
    }
}
