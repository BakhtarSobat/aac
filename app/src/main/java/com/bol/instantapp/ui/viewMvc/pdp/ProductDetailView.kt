package com.bol.instantapp.ui.viewMvc.pdp

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.DataBindingUtil.inflate
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import com.bol.instantapp.BR
import com.bol.instantapp.R
import com.bol.instantapp.databinding.ActivityMainBinding
import com.bol.instantapp.dto.Product
import com.bol.instantapp.ui.listeners.MyTextWatcher
import com.bol.instantapp.ui.viewMvc.ViewMvc


/**
 * Created by bsobat on 28/05/17.
 */
class ProductDetailView(val inflater: LayoutInflater, val listener: Listener) : ViewMvc {
    override var rootView: View? = null
    override var viewState: Bundle? = null


    private var eventHandler: EventHandler

    private var view: ActivityMainBinding

    private var model: Model

    init {
        eventHandler = EventHandler(listener);
        view = inflate(inflater, R.layout.activity_main, null, false);
        model = Model()
        view.model = model;
        view.handler = eventHandler;
        rootView = view.root;
    }


    fun populateProduct(product: Product) {
        model.title = product.title;
        model.description = "";
        if(!product.images.isEmpty()) {
            model.imageUrl = product.images.reversed().first().url;
        }

    }

    class Model : BaseObservable() {
        @Bindable var title: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.title)
            }
        @Bindable var description: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.description)
            }
        @Bindable var imageUrl: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.imageUrl)
            }

    }

    interface Listener {
        fun search(newValue: String)

    }


    class EventHandler(val listener: Listener) {
        fun queryChanged(): TextWatcher {

            return object : MyTextWatcher() {
                override fun onTextChanged(newValue: String) {
                    listener.search(newValue)
                }
            }
        }
    }
}