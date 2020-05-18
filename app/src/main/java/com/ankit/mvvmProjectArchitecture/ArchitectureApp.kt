package com.ankit.mvvmProjectArchitecture

import android.app.Application
import android.content.Context
import com.ankit.mvvmProjectArchitecture.di.component.ApplicationComponent
import com.ankit.mvvmProjectArchitecture.di.component.DaggerApplicationComponent


class ArchitectureApp : Application() {

    companion object {
        lateinit var instance: ArchitectureApp
            private set
    }

    val component: ApplicationComponent by lazy {
       DaggerApplicationComponent.builder().build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        component.inject(this)


    }

    override fun onTerminate() {
        super.onTerminate()
    }
}