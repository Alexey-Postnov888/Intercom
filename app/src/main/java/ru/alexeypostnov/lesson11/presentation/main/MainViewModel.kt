package ru.alexeypostnov.lesson11.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.alexeypostnov.lesson11.domain.CallApiUseCase

class MainViewModel(
    private val callApiUseCase: CallApiUseCase
): ViewModel() {

    init {
        callApi()
    }

    val state = MutableStateFlow<ByteArray?>(null)
    fun callApi() {
        viewModelScope.launch {
            callApiUseCase().apply {
                state.emit(this)
            }
        }
    }
}