package com.example.logyssey.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface

import com.example.logyssey.R
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.util.copy
import androidx.wear.compose.material.Scaffold
import com.example.logyssey.data.GameViewModel
import com.example.logyssey.screens.components.AppTopBar
import com.example.logyssey.screens.components.formatTimestamp

import com.example.logyssey.screens.components.reviewComponent
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun DashboardScreen(
        navController: NavController,
     viewModel: GameViewModel
    ) {
        var expandedReviews by remember { mutableStateOf(false) }
        val snackbarHostState = remember { SnackbarHostState() }

        val allGames by viewModel.allGames.collectAsState()
        val stats = viewModel.getDashboardStats()
        val favoriteGames = allGames.filter {
            it.isFavorite
        }
        val reviews by viewModel.reviews.collectAsState()
        val playingGames = allGames.filter {
            it.status == "Currently Playing"
        }
        val completedGames = allGames.filter {
            it.status == "Completed"
        }
        val wantToPlayGames = allGames.filter {
            it.status == "Want To Play"
        }
        val dashboardIsEmpty = favoriteGames.isEmpty() && playingGames.isEmpty() && completedGames.isEmpty() &&
                    wantToPlayGames.isEmpty() && reviews.isEmpty()
//confirm del
        var showDeleteDialog by remember { mutableStateOf(false) }
        var reviewToDelete by remember { mutableStateOf<Int?>(null) }

        val deleteTitle = stringResource(R.string.dashboard_Del)
        val deleteMessage = stringResource(R.string.dashboard_Del_confirm)
        val deleteBtn = stringResource(R.string.btn_Del)
        val cancelBtn = stringResource(R.string.btn_Delcancel)

        Scaffold(
            topBar = { dashboardTopbar() },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {


                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Text( text = stringResource(R.string.label_stats),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 18.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // hours card

                            Card(
                                modifier = Modifier
                                    .weight(1.4f)
                                    .height(180.dp),
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryFixed
                                ),
                                elevation = cardElevation(defaultElevation = 6.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(18.dp),

                                    horizontalAlignment = Alignment.End,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Start
                                    ) {

                                        Icon(
                                            imageVector = Icons.Default.Schedule,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.tertiary,
                                            modifier = Modifier.size(50.dp)
                                        )
                                    }
                                    Column (
                                        horizontalAlignment = Alignment.End
                                    ){

                                        Text(
                                            text =" ${stats.first.toInt().toString()+" " + stringResource(R.string.unit_hrs)}",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.tertiary,
                                            textAlign = TextAlign.End
                                        )
                                        Spacer(modifier = Modifier.height(16.dp))

                                        Text(
                                            text = stringResource(R.string.label_total_playtime),
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.tertiary,
                                            textAlign = TextAlign.End
                                        )
                                    }
                                }
                            }

                            // rating card
                           // fix
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(140.dp),
                                elevation = cardElevation(defaultElevation = 6.dp),
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor =
                                        MaterialTheme.colorScheme.outlineVariant
                                )
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(18.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.tertiaryFixed,
                                        modifier = Modifier.size(28.dp)
                                    )
                                        }
                                    Column {
                                        Text(
                                            text = String.format("%.1f", stats.second),
                                            style = MaterialTheme.typography.labelMedium,
                                            color = MaterialTheme.colorScheme.onTertiary,
                                            textAlign = TextAlign.Center
                                        )
                                        Text(
                                            text = stringResource(R.string.label_avg_rating),
                                            style = MaterialTheme.typography.bodyMedium,
                                            color = MaterialTheme.colorScheme.tertiaryFixed,
                                            textAlign = TextAlign.Center

                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                if (dashboardIsEmpty) {
                    item {

                        Box(
                            modifier = Modifier
                                .fillParentMaxSize()
                                .padding(30.dp),

                            contentAlignment = Alignment.Center
                        ) {

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Icon(
                                    imageVector = Icons.Default.SportsEsports,
                                    contentDescription = null,
                                    modifier = Modifier.size(70.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )

                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = stringResource(R.string.empty_dashboard),
                                    style = MaterialTheme.typography.headlineSmall,
                                    color = MaterialTheme.colorScheme.onBackground
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = stringResource(R.string.Alsoempty_dashboard),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }


                // favs
                if (favoriteGames.isNotEmpty()) {
                    item {
                        gamesRow(
                            categoryTitle = stringResource(R.string.list_favs),
                            games = favoriteGames,
                            onGameClick = { game -> navController.navigate("game_detail/${game.id}") },
                            snackbarHostState = snackbarHostState,

                            )
                    }
                }
                // playing rn
                if (playingGames.isNotEmpty()) {
                    item {
                        gamesRow(
                        categoryTitle = stringResource(R.string.list_playing),
                            games = playingGames,
                            onGameClick = { game ->
                                navController.navigate("game_detail/${game.id}")
                            },
                            snackbarHostState = snackbarHostState,

                            )
                    }
                }
                // COMPLETED
                if (completedGames.isNotEmpty()) {
                    item {
                        gamesRow(
                            categoryTitle = stringResource(R.string.list_completed),
                            games = completedGames,
                            onGameClick = { game ->
                                navController.navigate("game_detail/${game.id}")
                            },
                            snackbarHostState = snackbarHostState,
                            )
                    }
                }
                // revs
                if (reviews.isNotEmpty()) {
                    val displayedReviews =
                        if (expandedReviews)
                            reviews
                        else
                            reviews.take(2)
                    item {
                        SectionHeader(
                            title = stringResource(R.string.your_revs),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    val gameMap = allGames.associateBy { it.title }

                    items(displayedReviews) { review ->
                        val game = gameMap[review.gameTitle]

                        reviewComponent(
                            gamePoster = review.gamePoster,
                            gameTitle = review.gameTitle,
                            review = review.reviewContent,
                            rating = review.userRating.toInt(),
                            hours = review.hoursPlayed.toString(),
                            isFavorite = game?.isFavorite ?: false,
                            timestamp = formatTimestamp(review.timestamp),
                            onEditClick = {
                                navController.navigate(
                                "game_detail/${game?.id}?openSheet=true"
                            )},
                            onDeleteClick = {
                                reviewToDelete = review.reviewId
                                showDeleteDialog = true
                            },
                        )
                    }


                    // SHOW MORE / LESS BUTTON
                    if (reviews.size > 2) {

                        item {
                            TextButton(
                                onClick = {
                                    expandedReviews = !expandedReviews
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                Text(
                                    text =
                                        if (expandedReviews)
                                            stringResource(R.string.show_less)
                                        else
                                            stringResource(R.string.show_more),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                }
                // wanna play
                if (wantToPlayGames.isNotEmpty()) {
                    item {
                        gamesRow(
                            categoryTitle = stringResource(R.string.want_to_play),
                            games = wantToPlayGames,
                            onGameClick = { game ->
                                navController.navigate("game_detail/${game.id}")
                            },
                            snackbarHostState = snackbarHostState)

                    }
                }


                item {
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
        // delete confirm
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDeleteDialog = false
                    reviewToDelete = null
                },
                title = {
                    Text(deleteTitle,
                        style = MaterialTheme.typography.bodyMedium)
                },
                text = {
                    Text(deleteMessage,
                        style = MaterialTheme.typography.bodyMedium)
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            reviewToDelete?.let {
                                viewModel.deleteRev(it)
                            }
                            showDeleteDialog = false
                            reviewToDelete = null
                        }
                    ) {
                        Text(deleteBtn,
                            color = MaterialTheme.colorScheme.error)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDeleteDialog = false
                            reviewToDelete = null
                        }
                    ) {
                        Text(cancelBtn)
                    }
                }
            )
        }
    }

@Composable
fun dashboardTopbar() {
    Column(Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
        AppTopBar()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            verticalAlignment = Alignment.CenterVertically,
            ) {
            Text(
                stringResource(R.string.label_dash),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 20.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}