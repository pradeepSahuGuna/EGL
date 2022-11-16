package com.example.egl.di

import com.example.egl.retrofit.ProductAPI
import com.example.egl.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun providesProductsApi(retrofit: Retrofit): ProductAPI {
        return retrofit.create(ProductAPI::class.java)
    }
}