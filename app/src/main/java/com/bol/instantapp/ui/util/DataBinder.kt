package com.bol.instantapp.ui.util

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * Created by bsobat on 28/05/17.
 */
class DataBinder {

    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun setImageUrl(imageView: ImageView, url: String) {
            val context = imageView.getContext()
            Glide.with(context).load(url).into(imageView);
            //Picasso.with(context).load(url).fit().centerInside().error(R.drawable.no_image).placeholder(R.drawable.no_image).into(imageView);
        }
    }
}