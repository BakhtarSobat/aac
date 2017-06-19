package com.bol.instantapp

import android.app.Application
import com.bol.instantapp.di.AppComponent

/**
 * Created by bsobat on 26/05/17.
 */
class App: Application() {
    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent.create(this, "https://api.bol.com/");
    }


}