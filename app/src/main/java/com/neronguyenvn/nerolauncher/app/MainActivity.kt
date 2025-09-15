package com.neronguyenvn.nerolauncher.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.neronguyenvn.nerolauncher.core.designsystem.theme.NeroLauncherTheme
import com.neronguyenvn.nerolauncher.feature.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeroLauncherTheme {
                HomeScreen()
            }
        }
    }
}
