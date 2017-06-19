package com.bol.instantapp.di.modules

import com.bol.instantapp.Repository.CatalogRepository
import com.bol.instantapp.api.CatalogApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit



/**
 * Created by bsobat on 26/05/17.
 */
@Module
class ApiModule{
    @Provides
    @Singleton
    fun providesCatalogApi(retrofit: Retrofit): CatalogApi {
        return retrofit.create<CatalogApi>(CatalogApi::class.java)
    }
}