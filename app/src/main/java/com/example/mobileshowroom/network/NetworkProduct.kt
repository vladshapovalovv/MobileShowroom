package com.example.mobileshowroom.network

data class NetworkProduct(
	val image: String,
	val price: Double,
	val rating: Rating,
	val description: String,
	val id: Int,
	val title: String,
	val category: String
)

data class Rating(
	val rate: Any,
	val count: Int
)

