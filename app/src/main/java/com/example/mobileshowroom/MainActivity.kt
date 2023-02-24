package com.example.mobileshowroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.mobileshowroom.databinding.ActivityMainBinding
import com.example.mobileshowroom.domain.Product
import com.example.mobileshowroom.hilt.service.ProductService
import com.example.mobileshowroom.mapper.ProductMapper
import com.example.mobileshowroom.network.NetworkProduct
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var productService: ProductService

    @Inject
    lateinit var productMapper: ProductMapper

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = ProductEpoxyController()
        controller.setData(emptyList())
        binding.epoxyRecyclerView.setController(controller)

        lifecycleScope.launchWhenStarted {
            val response: Response<List<NetworkProduct>> = productService.getAllProducts()
            val domainProducts: List<Product> = response.body()!!.map {
                productMapper.buildFrom(networkProduct = it)
            }?: emptyList()
            controller.setData(domainProducts)

            if (domainProducts.isEmpty()) {
                Snackbar.make(binding.root, "Failed to fetch", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}