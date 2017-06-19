package com.bol.instantapp

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.widget.Toast
import com.bol.instantapp.dto.SearchResponse
import com.bol.instantapp.ui.viewMvc.pdp.ProductDetailView
import com.bol.instantapp.viewmodel.ProductViewModel
import com.bol.instantapp.viewmodel.Resource

class ProductDetailActivity : LifecycleActivity() {
    var productDetailViewModel: ProductViewModel? = null
    var view: ProductDetailView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = ProductDetailView(layoutInflater, object: ProductDetailView.Listener{
            override fun search(newValue: String) {
                productDetailViewModel?.search(newValue);
            }

        });
        setContentView(view?.rootView);

        productDetailViewModel = ProductViewModel.create(this);
        App.appComponent.inject(productDetailViewModel!!);

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
}
