package com.ankit.mvvmProjectArchitecture.di.component.modules

import android.app.Application
import com.ankit.mvvmProjectArchitecture.ArchitectureApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: ArchitectureApp) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return app
    }

    @Provides
    @Singleton
    fun provideArchitecture(): ArchitectureApp {
        return app
    }
}