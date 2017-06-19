package com.bol.instantapp.api

import com.bol.instantapp.dto.ProductDetailsResponse
import com.bol.instantapp.dto.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


/**
 * Created by bsobat on 26/05/17.
 */
interface CatalogApi {
    @GET("catalog/v4/search")
    abstract fun search(@Query("q") q: String, @Query("limit") limit: Int = 10, @Query("offset") offset: Int = 0): Call<SearchResponse>
}