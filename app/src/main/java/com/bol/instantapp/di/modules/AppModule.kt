package com.bol.instantapp.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by bsobat on 26/05/17.
 */
@Module
class AppModule(val application: android.app.Application){

    @Provides
    @Singleton
    fun provideApplication() : android.app.Application {
        return application;
    }
}