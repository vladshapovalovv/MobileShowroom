package com.example.mobileshowroom

import com.airbnb.epoxy.TypedEpoxyController
import com.example.mobileshowroom.domain.Product

class ProductEpoxyController: TypedEpoxyController<List<Product>>() {

    override fun buildModels(data: List<Product>?) {
        if(data == null || data.isEmpty()){
            repeat(7) {
                val epoxyId = it + 1
                ProductEpoxyModel(product = null).id(epoxyId).addTo(this)
            }
            return
        }

        data.forEach{ product ->
            ProductEpoxyModel(product).id(product.id).addTo(this)
        }
    }

}