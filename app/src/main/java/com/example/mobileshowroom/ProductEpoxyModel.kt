package com.example.mobileshowroom

import androidx.core.view.isGone
import androidx.core.view.isVisible
import coil.load
import com.example.mobileshowroom.databinding.EpoxyModelProductItemBinding
import com.example.mobileshowroom.domain.Product
import com.example.mobileshowroom.epoxy.ViewBindingKotlinModel
import java.text.NumberFormat

data class ProductEpoxyModel(
    val product: Product
) : ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {

    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    override fun EpoxyModelProductItemBinding.bind() {
        //Setup text
        productTitleTextView.text = product.title
        productDescriptionTextView.text = product.description
        productCategoryTextView.text = product.category
        productPriceTextView.text = currencyFormatter.format(product.price)

        //Image loading
        productImageViewLoadingProgressBar.isVisible = true
        productImageView.load(data = product.image) {
            listener { request, result ->
                productImageViewLoadingProgressBar.isGone = true
            }
        }
    }
}