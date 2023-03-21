package com.example.mobileshowroom.model

import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import coil.load
import com.example.mobileshowroom.R
import com.example.mobileshowroom.databinding.EpoxyModelProductItemBinding
import com.example.mobileshowroom.model.ui.UiProduct
import com.example.mobileshowroom.utils.ViewBindingKotlinModel
import java.text.NumberFormat

data class UiProductEpoxyModel(
    val uiProduct: UiProduct?
) : ViewBindingKotlinModel<EpoxyModelProductItemBinding>(R.layout.epoxy_model_product_item) {

    private val currencyFormatter = NumberFormat.getCurrencyInstance()

    override fun EpoxyModelProductItemBinding.bind() {

        shimmerLayout.isVisible = uiProduct == null
        cardView.isInvisible = uiProduct == null

        uiProduct?.let { UiProduct ->
            shimmerLayout.stopShimmer()

            //Setup text
            productTitleTextView.text = UiProduct.product.title
            productDescriptionTextView.text = UiProduct.product.description
            productCategoryTextView.text = UiProduct.product.category
            productPriceTextView.text = currencyFormatter.format(UiProduct.product.price)


            // Favorite icon
            val imageRes = if (this@UiProductEpoxyModel.uiProduct.isFavorite) {
                R.drawable.ic_round_favorite_24
            } else {
                R.drawable.ic_round_favorite_border_24
            }
            favoriteImageView.setIconResource(imageRes)

            //Image loading
            productImageViewLoadingProgressBar.isVisible = true
            productImageView.load(data = UiProduct.product.image) {
                listener { request, result ->
                    productImageViewLoadingProgressBar.isGone = true
                }
            }
        } ?: shimmerLayout.startShimmer()
    }
}