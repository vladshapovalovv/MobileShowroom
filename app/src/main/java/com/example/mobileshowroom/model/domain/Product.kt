package com.example.mobileshowroom.model.domain

import java.math.BigDecimal

data class Product(
    val image: String,
    val price: BigDecimal,
    val description: String,
    val id: Int,
    val title: String,
    val category: String
)