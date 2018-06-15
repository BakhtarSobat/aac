package com.bol.instantapp.ui.viewMvc.pdp

import com.bol.instantapp.dto.Product
import com.bol.instantapp.ui.viewMvc.ViewMvc

interface ProductDetailInterfaceView: ViewMvc {
    fun populateProduct(first: Product)
}