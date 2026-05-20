package com.example.logyssey.screens


import android.R.attr.maxWidth
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.material3.MaterialTheme

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection

import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.logyssey.R


import com.example.logyssey.data.GameDashboard

import com.example.logyssey.data.GameEntity
import com.example.logyssey.data.GameViewModel
import com.example.logyssey.data.NewsDataSource
import com.example.logyssey.data.getLocalizedDescription
import com.example.logyssey.data.getLocalizedTitle
import com.example.logyssey.screens.components.AppTopBar
import com.example.logyssey.screens.components.GameCard
import kotlinx.coroutines.CoroutineScope


@Composable
fun HomeScreen(navController: NavController, onThemeToggle: () -> Unit, viewModel: GameViewModel) {
    var selectedTab by remember { mutableStateOf(0) } //state
    val dashboard by viewModel.dashboardData.collectAsState() //Auto update when data changes
    val currentLang = LocalConfiguration.current.locales[0].language
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            HomeTopBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

            when (selectedTab) {
                0 -> {
                    dashboard?.let { data ->
                        GameContent(
                            dashboard = data,
                            padding = innerPadding,
                            navController = navController,
                            snackbarHostState = snackbarHostState,
                            )
                    } ?: Box(modifier = Modifier.fillMaxSize()) {

                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))  //show a loading spinner while DB is reading
                    }
                }

                1 -> NewsContent(innerPadding,currentLang)
            }

    }
}

@Composable
fun GameContent(
    dashboard: GameDashboard,
    padding: PaddingValues,
    navController: NavController,
    snackbarHostState: SnackbarHostState,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        item {
            gamesRow(
                categoryTitle = stringResource(R.string.cat_latest_releases),
                games = dashboard.latestReleases,
                onGameClick = { game ->
                    navController.navigate("game_detail/${game.id}") },
                snackbarHostState = snackbarHostState,
            )
        }

        item {
            gamesRow(
                categoryTitle = stringResource(R.string.cat_popular),
                games = dashboard.popularGames,
                onGameClick = { game -> navController.navigate("game_detail/${game.id}") },
                snackbarHostState = snackbarHostState,
            )
        }
        item {
            gamesRow(
                categoryTitle = stringResource(R.string.cat_upcoming) ,
                games = dashboard.upcomingGames,
                onGameClick = { game -> navController.navigate("game_detail/${game.id}") },
                snackbarHostState = snackbarHostState,
            )
        }


    }
}

@Composable
fun SegmentedControl(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {


    Box(
        modifier = Modifier
            .padding(16.dp)
            .height(40.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(30))
            .background(MaterialTheme.colorScheme.inverseSurface)
    ) {
        val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl
        val targetOffset = if (selectedTab == 0) 0.dp else if (isRtl) (-190).dp else 190.dp


        Box(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxHeight()
                .fillMaxWidth(0.5f)
                .offset(x = if (selectedTab == 0) 0.dp else 190.dp)
                .shadow(8.dp, RoundedCornerShape(30),)
                .background(MaterialTheme.colorScheme.primary, RoundedCornerShape(30))
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tab 1
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { onTabSelected(0) },
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.tab_games),
                    color = if (selectedTab == 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface)
            }
            // Tab 2
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clickable { onTabSelected(1) },
                contentAlignment = Alignment.Center
            ) {
                Text(stringResource(R.string.tab_whats_new), color = if (selectedTab == 1) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}
@Composable
fun HomeTopBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Column(Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
        AppTopBar()
        SegmentedControl(
            selectedTab = selectedTab,
            onTabSelected = onTabSelected
        )
    }
}


@Composable
fun gamesRow(
    categoryTitle: String,
    games: List<GameEntity>,
    onGameClick: (GameEntity) -> Unit,
    snackbarHostState: SnackbarHostState,
) {
    val scope = rememberCoroutineScope()
    Column {

        SectionHeader(title = categoryTitle,  color = MaterialTheme.colorScheme.onBackground)
        LazyRow(
            contentPadding = PaddingValues(horizontal = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(0.5.dp)
        ) {
            items(games) { game ->
                GameCard(
                    game = game,
                    viewModel = viewModel(),
                    onClick = { onGameClick(game) },
                    snackbarHostState = snackbarHostState,
                    scope = scope
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically


    ) {
        Text( text = title, style = MaterialTheme.typography.bodyLarge, color = color,
        )

    }
}



@Composable
fun NewsContent(padding: PaddingValues,currentLanguage: String) {

    val newsList = NewsDataSource.newsList

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(padding),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(newsList) { item ->
            NewsCard(item.getLocalizedTitle(currentLanguage),
                description = item.getLocalizedDescription(currentLanguage)
                ,item.photo)
        }
    }
}

@Composable
fun NewsCard(title: String, description: String, photo: String) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
          elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            //   image
            AsyncImage(
                model = photo,
                contentDescription = "News Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )

            // Text
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(16.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.outline,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp),
                            color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}






