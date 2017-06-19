package com.bol.instantapp.di.modules

import com.bol.instantapp.Repository.CatalogRepository
import com.bol.instantapp.api.CatalogApi
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by bsobat on 26/05/17.
 */
@Module
class RepositoryModule{
    @Provides
    @Singleton
    fun provideCatalogRepository(api: CatalogApi): CatalogRepository{
        return CatalogRepository(api)
    }
}