package com.example.egl.retrofit

import com.example.egl.models.Products
import retrofit2.Response
import retrofit2.http.GET

interface ProductAPI {
    @GET("v3/2f06b453-8375-43cf-861a-06e95a951328")
    suspend fun getProducts():Response<Products>
}