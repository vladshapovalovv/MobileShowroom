package com.example.mobileshowroom.model.ui

import com.example.mobileshowroom.model.domain.Product

data class UiProduct(
    val product: Product,
    val isFavorite: Boolean = false

)