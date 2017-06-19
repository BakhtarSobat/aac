package com.bol.instantapp.di

import android.app.Activity
import android.app.Application
import android.content.Context
import com.bol.instantapp.Repository.CatalogRepository
import com.bol.instantapp.di.modules.ApiModule
import dagger.Component
import javax.inject.Singleton
import com.bol.instantapp.di.modules.AppModule;
import com.bol.instantapp.di.modules.NetModule
import com.bol.instantapp.di.modules.RepositoryModule
import com.bol.instantapp.viewmodel.ProductViewModel


/**
 * Created by bsobat on 26/05/17.
 */
@Singleton
@Component(
        modules = arrayOf(AppModule::class, NetModule::class, RepositoryModule::class, ApiModule::class)
)
interface AppComponent {
    fun inject(viewModelModule: ProductViewModel)
    fun inject(activity: Context)

    fun provideCatalogRepository(): CatalogRepository;

    companion object Factory{
        fun create(app: Application, baseUrl: String): AppComponent {
            val appComponent = DaggerAppComponent.builder().
                    appModule(AppModule(app)).
                    apiModule(ApiModule()).
                    netModule(NetModule(baseUrl)).
                    repositoryModule(RepositoryModule()).
                    build();
            return appComponent
        }
    }
}