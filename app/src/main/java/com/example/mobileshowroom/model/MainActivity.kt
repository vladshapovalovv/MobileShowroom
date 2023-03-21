package com.example.mobileshowroom.model

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.mobileshowroom.databinding.ActivityMainBinding
import com.example.mobileshowroom.model.ui.UiProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = UiProductEpoxyController()
        controller.setData(emptyList())
        binding.epoxyRecyclerView.setController(controller)

        combine(
            viewModel.store.stateFlow.map { it.products },
            viewModel.store.stateFlow.map { it.favoriteProductIds  }
        ){listOfProducts, setOfFavoriteIds ->
            listOfProducts.map { product ->
                UiProduct(product = product, isFavorite = setOfFavoriteIds.contains(product.id))
            }
        }.distinctUntilChanged().asLiveData().observe(this){ uiProducts->
            controller.setData(uiProducts)
        }
        viewModel.refreshProducts()
    }
}
