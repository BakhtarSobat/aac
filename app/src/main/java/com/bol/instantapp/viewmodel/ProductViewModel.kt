package com.bol.instantapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations.switchMap
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.bol.instantapp.Repository.CatalogRepository
import com.bol.instantapp.manager.FeatureToggleManager
import javax.inject.Inject

/**
 * Created by bsobat on 26/05/17.
 */
class ProductViewModel: ViewModel(){
    val searchInfput: MutableLiveData<String> = MutableLiveData()

    val searchResult = switchMap(searchInfput){
        if(it.length >= 1) {
            repository.doSearch(it)
        } else {
            MutableLiveData();
        }
    }

    private lateinit  var repository: CatalogRepository;

    lateinit var featureToggleManager: FeatureToggleManager

    @Inject fun init(repository: CatalogRepository, featureToggleManager: FeatureToggleManager) {
        this.repository = repository;
        this.featureToggleManager = featureToggleManager

    }

    fun search(term: String){
        searchInfput.value = (term)
    }

    companion object{
        fun create(activity: FragmentActivity): ProductViewModel{
            var productDetailViewModel = ViewModelProviders.of(activity).get(ProductViewModel::class.java)
            return productDetailViewModel
        }
    }
}