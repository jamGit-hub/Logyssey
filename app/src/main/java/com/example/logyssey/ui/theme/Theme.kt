package com.example.logyssey.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.logyssey.ui.theme.LightMidnight

private val DarkColorScheme = darkColorScheme(
    primary = GoldAccent,
    secondary = MutedLavender,
    background =  MidnightDark,
    surface = PureBlack,
    onPrimary = PureBlack,
    onBackground = OffWhite,
    onSurface = GoldAccent,
    inverseSurface = PureBlack,
    outline = GoldAccent,
    outlineVariant = MutedLavender,
    primaryFixed = PureBlack,
    onSecondaryContainer= MidnightDark,
    secondaryFixed= LightMidnight,
    surfaceContainer= PureBlack,
    surfaceTint = OffWhite,
    tertiary= GoldAccent,
    onTertiary= GoldAccent,
    tertiaryFixed = PureBlack,
    onTertiaryContainer = ChampagneLight
    )

private val LightColorScheme = lightColorScheme(

    // Secondary
    background = OffWhite ,  // main screen
    surface = ChampagneLight,   // Card backgrounds
    onPrimary = OffWhite,
    onBackground = PureBlack,   // Main text color
    onSurface = MidnightDark ,
    inverseSurface = OffWhite,
    primary = GoldAccent,
    secondary = MutedLavender,
    outline= MidnightDark ,
    outlineVariant = MidnightDark,
    primaryFixed = MidnightDark,
    onSecondaryContainer = ChampagneLight,
    secondaryFixed= LightMidnight,
    surfaceContainer= LightMidnight,
    surfaceTint = OffWhite,
    tertiary= OffWhite,
    onTertiary = ChampagneLight,
    tertiaryFixed = GoldAccent,
    onTertiaryContainer = LightMidnight



)

@Composable
fun LogysseyTheme(  darkTheme: Boolean = isSystemInDarkTheme(),
                    dynamicColor: Boolean = false,
                    content: @Composable () -> Unit
) {

    //if (darkTheme) DarkColorScheme else LightColorScheme
    val colorScheme = when {
dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
    val context = LocalContext.current
    if(darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
}
        darkTheme -> DarkColorScheme
        else -> LightColorScheme

    }


    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


