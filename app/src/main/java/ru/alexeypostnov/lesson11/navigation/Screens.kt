package ru.alexeypostnov.lesson11.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import ru.alexeypostnov.lesson11.presentation.main.MainScreen

class MainNavigationScreen: Tab {
    override val options: TabOptions
        @Composable
        get() {
            val icon = rememberVectorPainter(
                Icons.Default.Home
            )
            return remember {
                TabOptions(
                    index = 0u,
                    title = "Home",
                    icon = icon
                )
            }
        }
    @Composable
    override fun Content() {
        MainScreen()
    }
}