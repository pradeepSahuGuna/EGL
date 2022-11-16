package com.example.egl.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.egl.models.Products
import com.example.egl.repository.ProductRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: ProductRepository) : ViewModel() {
    val productsLiveData: LiveData<Products>
        get() = repository.products

    init {
        viewModelScope.launch {
            repository.getProducts()
        }
    }
}