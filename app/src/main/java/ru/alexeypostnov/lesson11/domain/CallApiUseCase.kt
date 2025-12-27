package ru.alexeypostnov.lesson11.domain

import ru.alexeypostnov.lesson11.data.repository.MyRepository

interface CallApiUseCase {
    suspend operator fun invoke(): ByteArray
}

class CallApiUseCaseImpl(
    private val repository: MyRepository
): CallApiUseCase {
    override suspend fun invoke(): ByteArray =
        repository.getImage()
}