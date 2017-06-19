package com.bol.instantapp.di.modules

import android.app.Application
import dagger.Provides
import javax.inject.Singleton
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import dagger.Module
import okhttp3.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


/**
 * Created by bsobat on 26/05/17.
 */
@Module
class NetModule (val baseUrl: String){
    val QUERY_PARAMETER = "apikey"
    val KEY = "--"// get your bol.com api key at https://partnerblog.bol.com/documentatie/open-api/

    @Provides
    @Singleton
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024L
        val cache = Cache(application.cacheDir, cacheSize)
        return cache
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache, interceptor: Interceptor): OkHttpClient {
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
        client.cache(cache)
        return client.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor{
        return Interceptor { chain ->
            //copy and alter url
            val url = chain
                    .request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(QUERY_PARAMETER, KEY)
                    .addQueryParameter("format", "json")
                    .build()

            //copy and alter request
            val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

            chain.proceed(request)
        }
    }
}