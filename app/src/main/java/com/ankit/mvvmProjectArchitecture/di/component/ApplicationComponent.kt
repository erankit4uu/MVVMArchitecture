package com.ankit.mvvmProjectArchitecture.di.component

import android.app.Application
import com.ankit.mvvmProjectArchitecture.di.component.modules.NetworkModule
import com.ankit.mvvmProjectArchitecture.ui.baseActivity.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface ApplicationComponent {
    fun inject(application: Application)

//    activities


    fun inject(activity: BaseActivity)
}