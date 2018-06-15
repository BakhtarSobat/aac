package com.bol.instantapp

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Toast
import com.bol.instantapp.dto.SearchResponse
import com.bol.instantapp.ui.viewMvc.pdp.ModernProductDetailView
import com.bol.instantapp.ui.viewMvc.pdp.ProductDetailInterfaceView
import com.bol.instantapp.ui.viewMvc.pdp.ProductDetailView
import com.bol.instantapp.viewmodel.ProductViewModel
import com.bol.instantapp.viewmodel.Resource

class ProductDetailActivity : FragmentActivity() {
    val productDetailViewModel by lazy { createViewModel() }
    var view: ProductDetailInterfaceView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = if (productDetailViewModel.featureToggleManager.config.newLayout) {
            ModernProductDetailView(layoutInflater, object : ModernProductDetailView.Listener {
                override fun search(newValue: String) {
                    productDetailViewModel?.search(newValue);
                }

            })

        } else {
            ProductDetailView(layoutInflater, object : ProductDetailView.Listener {
                override fun search(newValue: String) {
                    productDetailViewModel?.search(newValue);
                }

            })
        }
        setContentView(view?.rootView);

        productDetailViewModel?.searchResult?.observe(this, Observer<Resource<SearchResponse>> { resource ->
            if (resource != null) {
                when (resource.status) {
                    Resource.Status.SUCCESS -> {
                        val product = resource.data;
                        val products = product?.products;
                        if (products != null) {
                            if (!products.isEmpty()) {
                                view?.populateProduct(products.first())
                            }
                        }
                    }
                    Resource.Status.ERROR->{
                        Toast.makeText(this, "Error: "+resource.exception?.message, Toast.LENGTH_LONG);
                    }
                    Resource.Status.LOADING->{

                    }
                }
            }
        })
    }

    private fun createViewModel(): ProductViewModel {
        val productDetailViewModel = ProductViewModel.create(this).apply {
            App.appComponent.inject(this)
        }

        return productDetailViewModel
    }
}
