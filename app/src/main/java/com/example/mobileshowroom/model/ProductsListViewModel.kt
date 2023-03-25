package com.example.mobileshowroom.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileshowroom.model.domain.Product
import com.example.mobileshowroom.redux.ApplicationState
import com.example.mobileshowroom.redux.Store
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsListViewModel @Inject constructor(

    val store: Store<ApplicationState>,
    private val productsRepository: ProductsRepository

) : ViewModel() {

    fun refreshProducts() = viewModelScope.launch {
        val products: List<Product> = productsRepository.fetchAllProducts()
        store.update { applicationState ->
            return@update applicationState.copy(
                products = products
            )
        }
    }
}
