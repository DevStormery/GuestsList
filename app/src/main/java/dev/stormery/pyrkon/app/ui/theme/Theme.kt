package dev.stormery.pyrkon.app.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF4A265B),
    secondary = Color(0xFF018293),
    background = Color(0xFF1C1B1F),
    surface = Color(0xFF121212),
    onPrimary = Color(0xFFF8F8F8),
    onSecondary = Color(0xFF121212),
    onBackground = Color(0xFFCCCCCC),
    onSurface = Color(0xFFFFFFFF),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4A265B),
    secondary = Color(0xFF018293),
    background = Color(0xFFEFF9F9),
    surface = Color(0xFFE4E4E4),
    onPrimary = Color.White,
    onSecondary = Color(0xFF121212),
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
)

@Composable
fun PyrkonTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}