package com.example.mobileshowroom.hilt.service

import com.example.mobileshowroom.network.NetworkProduct
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getAllProducts(): Response<List<NetworkProduct>>
}