package com.example.egl.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.egl.models.Products
import com.example.egl.retrofit.ProductAPI
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productAPI: ProductAPI) {

    private val _products = MediatorLiveData<Products>()
    val products: LiveData<Products>
        get() = _products

    suspend fun getProducts() {
        val result = productAPI.getProducts()
        if (result.isSuccessful && result.body() != null) {
            _products.postValue(result.body())
        }
    }
}