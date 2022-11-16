package com.example.egl.di

import com.example.egl.ui.fragments.ProductListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {
    fun inject(mainActivity: ProductListFragment)
}