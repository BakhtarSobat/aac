package com.bol.instantapp.dto

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class Product(val id: String, val title: String = "", val images: List<Image> = ArrayList(), val longDescription: String = "", val shortDescription: String = "")
data class Image(@SerializedName("key")
                 val size: String = "M", @SerializedName("url")
                 val url: String = "")
data class ProductDetailsResponse (val products: List<Product>? = ArrayList())
data class SearchResponse (val products: List<Product> = ArrayList())
