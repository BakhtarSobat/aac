package com.bol.instantapp.ui.listeners

import android.text.Editable
import android.text.TextWatcher



/**
 * Created by bsobat on 28/05/17.
 */
abstract class MyTextWatcher : TextWatcher {

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable) {
        onTextChanged(s.toString())
    }

    abstract fun onTextChanged(newValue: String)
}