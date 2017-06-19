package com.bol.instantapp.Repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.bol.instantapp.api.CatalogApi
import com.bol.instantapp.dto.ProductDetailsResponse
import com.bol.instantapp.dto.SearchResponse
import com.bol.instantapp.exception.AppException
import com.bol.instantapp.viewmodel.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject

/**
 * Created by bsobat on 26/05/17.
 */

class CatalogRepository @Inject constructor(val api: CatalogApi ) {

    fun doSearch(q: String, limit: Int = 1, offset: Int = 0): LiveData<Resource<SearchResponse>>{
        val data = MutableLiveData<Resource<SearchResponse>>();

        api.search(q, limit, offset).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                data.value = Resource.success(response?.body());
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                val exception = AppException(t)
                data.value = Resource.error( exception)
            }
        });
        return data;
    }
}
