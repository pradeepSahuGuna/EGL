package com.example.egl

import android.app.Application
import com.example.egl.di.ApplicationComponent
import com.example.egl.di.DaggerApplicationComponent
import dagger.Component

 class ProductApplication : Application() {
      lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()

    }
}