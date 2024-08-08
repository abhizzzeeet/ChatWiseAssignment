package com.example.chatwiseassignment.recyclerView


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwiseassignment.R
import com.example.chatwiseassignment.models.Review

class ReviewAdapter(private val reviews: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        holder.reviewerName.text = review.reviewerName
        holder.comment.text = review.comment
        holder.ratingBar.rating = review.rating.toFloat()
    }

    override fun getItemCount(): Int = reviews.size

    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val reviewerName: TextView = itemView.findViewById(R.id.reviewReviewerName)
        val comment: TextView = itemView.findViewById(R.id.reviewComment)
        val ratingBar: RatingBar = itemView.findViewById(R.id.reviewRatingBar)
    }
}
