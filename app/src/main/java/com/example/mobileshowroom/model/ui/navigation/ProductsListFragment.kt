package com.example.mobileshowroom.model.ui.navigation

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.example.mobileshowroom.R
import com.example.mobileshowroom.databinding.FragmentProductsListBinding
import com.example.mobileshowroom.model.MainActivity
import com.example.mobileshowroom.model.ProductsListViewModel
import com.example.mobileshowroom.model.UiProductEpoxyController
import com.example.mobileshowroom.model.ui.UiProduct
import com.example.mobileshowroom.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class ProductsListFragment : BaseFragment(R.layout.fragment_products_list) {

    private val binding: FragmentProductsListBinding by viewBinding()
    private val viewModel: ProductsListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val controller = UiProductEpoxyController(viewModel)
        controller.setData(emptyList())
        binding.epoxyRecyclerView.setController(controller)
        combine(
            viewModel.store.stateFlow.map { it.products },
            viewModel.store.stateFlow.map { it.favoriteProductIds  }
        ){listOfProducts, setOfFavoriteIds ->
            listOfProducts.map { product ->
                UiProduct(product = product, isFavorite = setOfFavoriteIds.contains(product.id))
            }
        }.distinctUntilChanged().asLiveData().observe(viewLifecycleOwner){ uiProducts->
            controller.setData(uiProducts)
        }
        viewModel.refreshProducts()
    }
}