package ru.alexeypostnov.lesson11.di

import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.alexeypostnov.lesson11.data.AuthIntercept
import ru.alexeypostnov.lesson11.data.network.MyApi
import ru.alexeypostnov.lesson11.data.repository.MyRepository
import ru.alexeypostnov.lesson11.data.repository.MyRepositoryImpl
import ru.alexeypostnov.lesson11.domain.CallApiUseCase
import ru.alexeypostnov.lesson11.domain.CallApiUseCaseImpl
import ru.alexeypostnov.lesson11.presentation.main.MainViewModel
import java.util.concurrent.TimeUnit

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("http://192.168.0.104/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create<MyApi>()
    } bind MyApi::class

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthIntercept>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    } bind OkHttpClient::class

    single {
        AuthIntercept()
    } bind AuthIntercept::class

    singleOf(::MyRepositoryImpl) bind(MyRepository::class)

    factoryOf(::CallApiUseCaseImpl) bind(CallApiUseCase::class)

    single {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(get<AuthIntercept>())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()

        ImageLoader.Builder(get())
            .components {
                add(OkHttpNetworkFetcherFactory(okHttpClient))
            }
            .build()
    } bind ImageLoader::class
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}