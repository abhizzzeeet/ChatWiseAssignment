package com.example.chatwiseassignment.retrofit


import com.example.chatwiseassignment.models.ProductsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    suspend fun getProducts() : Response<ProductsResponse>
}