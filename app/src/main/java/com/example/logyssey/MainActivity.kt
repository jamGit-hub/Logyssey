package com.example.logyssey

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

import com.example.logyssey.navigation.MainApp
import com.example.logyssey.ui.theme.LogysseyTheme
import java.util.Locale


class MainActivity : ComponentActivity() {
    @SuppressLint("LocalContextConfigurationRead")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var languageCode by remember { mutableStateOf("en") }
            var darkTheme by remember { mutableStateOf(true) }
            val locale = Locale(languageCode)
            Locale.setDefault(locale)
            val configuration = Configuration(LocalConfiguration.current).apply {
                setLocale(locale)
                setLayoutDirection(locale)
            }
            val context = LocalContext.current
            val localizedContext = remember(languageCode) {
                val config = Configuration(context.resources.configuration)
                config.setLocale(locale)
                context.createConfigurationContext(config)
            }

            CompositionLocalProvider(
                LocalConfiguration provides configuration,
                LocalLayoutDirection provides if (languageCode == "ar") LayoutDirection.Rtl else LayoutDirection.Ltr,
                LocalContext provides localizedContext
            ) {
                LogysseyTheme(darkTheme = darkTheme) {
                    MainApp(
                        darkTheme = darkTheme,
                        onToggleTheme = { darkTheme = !darkTheme },
                        currentLanguage = languageCode,
                        onLanguageChange = { newLang -> languageCode = newLang }
                    )
                }
            }
        }
    }
}