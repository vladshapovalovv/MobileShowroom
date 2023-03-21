package com.example.mobileshowroom.redux

import com.example.mobileshowroom.model.domain.Product

data class ApplicationState(

    val products: List<Product> = emptyList(),
    val favoriteProductIds: Set<Int> = emptySet()

)