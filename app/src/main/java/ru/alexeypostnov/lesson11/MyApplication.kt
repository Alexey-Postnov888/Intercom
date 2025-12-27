package ru.alexeypostnov.lesson11

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.alexeypostnov.lesson11.di.appModule
import ru.alexeypostnov.lesson11.di.viewModelModule

class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(
                appModule,
                viewModelModule
            )
        }
    }
}