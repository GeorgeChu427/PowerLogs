package com.george.powerlogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.george.powerlogs.ui.screens.main.MainScreen
import com.george.powerlogs.ui.theme.PowerLogsTheme
import com.george.powerlogs.ui.screens.main.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PowerLogsTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}