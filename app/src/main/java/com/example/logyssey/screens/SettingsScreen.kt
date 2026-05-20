package com.example.logyssey.screens

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.navigation.NavHostController
import com.example.logyssey.R
import com.example.logyssey.screens.components.AppTopBar


@Composable
fun LanguageOption(label: String, selected: Boolean, onClick: () -> Unit,) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                onClick = onClick,
                role = Role.RadioButton
            )
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = null // the row handles the click
        )
        Text(
            text = label,
            modifier = Modifier.padding(start = 16.dp),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground,

            )
    }
}

@Composable
fun SettingsScreen(isDarkTheme: Boolean,
 onToggleTheme: () -> Unit, navController: NavHostController ,
                   currentLanguage: String,
                   onLanguageChange: (String) -> Unit

) {
val op = Text(stringResource(R.string.lang_en),
    )
    Scaffold(
        topBar = { AppTopBar() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth().height(60.dp).background(color = MaterialTheme.colorScheme.onPrimary),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Text(
                    stringResource(R.string.title_app_settings),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 20.dp),
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.label_dark_mode),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onBackground

                    )
                    Switch(
                        checked = isDarkTheme,
                        onCheckedChange = { onToggleTheme() }
                    )
                }

                HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                Text(
                    text = stringResource(R.string.label_language),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = MaterialTheme.colorScheme.onBackground

                )

                // Language Radio Buttons
                Column {
                    LanguageOption(
                        label = stringResource(R.string.lang_en),
                        selected = currentLanguage == "en",
                        onClick = {
                            onLanguageChange("en") //  updates MainActivity directly
                        }
                    )
                    LanguageOption(
                        label =  stringResource(R.string.lang_ar) ,

                        selected = currentLanguage == "ar",
                                onClick = {
                            onLanguageChange("ar")
                        }
                    )
                }
            }
        }
    }
}



