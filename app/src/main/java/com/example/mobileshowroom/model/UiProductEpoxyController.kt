package com.example.mobileshowroom.model

import com.airbnb.epoxy.TypedEpoxyController
import com.example.mobileshowroom.model.ui.UiProduct

class UiProductEpoxyController: TypedEpoxyController<List<UiProduct>>() {

    override fun buildModels(data: List<UiProduct>?) {
        if(data == null || data.isEmpty()){
            repeat(7) {
                val epoxyId = it + 1
                UiProductEpoxyModel(uiProduct = null).id(epoxyId).addTo(this)
            }
            return
        }

        data.forEach{ uiProduct ->
            UiProductEpoxyModel(uiProduct).id(uiProduct.product.id).addTo(this)
        }
    }

}