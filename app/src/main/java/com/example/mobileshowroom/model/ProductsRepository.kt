package com.example.mobileshowroom.model

import com.example.mobileshowroom.model.domain.Product
import com.example.mobileshowroom.hilt.service.ProductService
import com.example.mobileshowroom.model.mapper.ProductMapper
import javax.inject.Inject

class ProductsRepository @Inject constructor(

    private val productService: ProductService,
    private val productMapper: ProductMapper
) {

    suspend fun fetchAllProducts(): List<Product> {
        return productService.getAllProducts().body()?.let { networkProducts ->
            networkProducts.map { productMapper.buildFrom(it) }
        } ?: emptyList()
    }
}