package com.example.logyssey.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

import androidx.wear.compose.navigation.currentBackStackEntryAsState
import com.example.logyssey.R


@Composable
fun AppTopBar(){
    Column(Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
        Text(
            "LOGYSSEY",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.fillMaxWidth().padding(0.0.dp, 20.0.dp, 0.0.dp, 10.0.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}


@Composable
fun AppBottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = MaterialTheme.colorScheme.surface) {
        val items = listOf(
            Pair("home", R.drawable.fort__1),
            Pair("explore", R.drawable.search),
            Pair("Dashboard", R.drawable.trophy),
            Pair("settings", R.drawable.gears)
        )

        items.forEach { (route, iconRes) ->
            NavigationBarItem(
                selected = currentRoute == route,
                onClick = {
                    navController.navigate(route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface, // The color when selected
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground, // The color when NOT selected
                    indicatorColor = MaterialTheme.colorScheme.primary),
                icon = { Icon(painter = painterResource(id = iconRes), contentDescription = route) }
            )
        }
    }
}

