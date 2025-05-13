package dev.stormery.pyrkon.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.stormery.pyrkon.app.navigation.NavigationHost
import dev.stormery.pyrkon.app.ui.theme.PyrkonTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PyrkonTheme {
                val navController = rememberNavController()
                NavigationHost(navController)
            }
        }
    }
}

