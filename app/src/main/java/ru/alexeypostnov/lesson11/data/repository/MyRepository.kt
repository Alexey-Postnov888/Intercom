package ru.alexeypostnov.lesson11.data.repository

import ru.alexeypostnov.lesson11.data.network.MyApi

interface MyRepository {
    fun callApi(): String
    suspend fun getImage(): ByteArray
}

class MyRepositoryImpl(
    private val service: MyApi
): MyRepository {
    override fun callApi(): String {
        return service.callApi()
    }

    override suspend fun getImage(): ByteArray =
        service.getImage().body()?.bytes() ?: byteArrayOf()
}
