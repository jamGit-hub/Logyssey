package com.example.logyssey.screens

import android.R.attr.layoutDirection
import android.R.attr.rating
import android.R.id.shareText
import android.content.Intent
import android.content.res.Configuration
import androidx.camera.core.Preview
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Close
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star


import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import coil.compose.AsyncImage
import com.example.logyssey.data.GameEntity
import com.example.logyssey.data.GameViewModel
import com.example.logyssey.data.ReviewEntity
import com.example.logyssey.screens.components.AppTopBar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.logyssey.R
import com.example.logyssey.data.GameRepository
import com.example.logyssey.data.getLocalizedDescription
import com.example.logyssey.data.getLocalizedGenre
import kotlinx.coroutines.launch


fun Long.toReadableDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
    return format.format(date)
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameDetailScreen(
    gameId: String?,
                     navController: NavController,
                     viewModel: GameViewModel,
                     currentLanguage: String,
                     openSheet: Boolean = false
) {
    val id = gameId?.toIntOrNull() ?: 0 // observes all games from db and find the specific one
    val allGames by viewModel.allGames.collectAsState()
    val game = allGames.find { it.id == id }
    if (game == null) { return }
    val focusManager = LocalFocusManager.current
    var showSheet by remember { mutableStateOf(openSheet) }
    var rating by remember { mutableIntStateOf(0) }
    var reviewText by remember { mutableStateOf("") }
    var hoursPlayed by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()
    LaunchedEffect(game?.id) {
// filling the sheet if review exists
        game?.let {
            val existingReview =
                viewModel.getReviewByGameId(it.id)
            if (existingReview != null) {
                rating = existingReview.userRating.toInt()
                reviewText = existingReview.reviewContent
                hoursPlayed = existingReview.hoursPlayed.toString()
            }
        }
    }
// snckbar notifivation when clicking btns
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val savedMessage = stringResource(R.string.dashboard_changes)
    val WannaPlayMessage = stringResource(R.string.dashboard_wanna_play)
//similar list row
    val similarList = remember(game, allGames) {
        getSimilarGames(currentGame = game, allGames = allGames)
    }


    if (game == null) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator() // loading while DB is queried
    }}
    else {
        Scaffold(
            modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = { focusManager.clearFocus()
            })
        },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = { GDTopBar(navController) },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {

                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.onPrimary)
                ) {
                    AsyncImage(
                        model = game.Poster,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth().fillMaxHeight(),
                        contentScale = ContentScale.Crop
                    )
                }
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = game.title,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = stringResource(R.string.label_release)+" ${game.releaseDate.toReadableDate()} ",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    if (!game.isUpcoming) {
                        Text(
                            text = stringResource(R.string.label_ps_rating) + "${game.PSrating} \n" + stringResource(
                                R.string.label_steam_rating
                            ) + "${game.Steamrating}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(20.dp, bottom = 0.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
// the saving sheet BTN
                        Button(
                            onClick = { showSheet = true },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.outlineVariant)

                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.outline_bookmark_24),
                                    contentDescription = "Bookmark",
                                    tint = MaterialTheme.colorScheme.background,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                                Text(
                                    stringResource(R.string.btn_log_action),
                                    color = MaterialTheme.colorScheme.background,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
// WANT to play BTN
                        Button(
                            onClick = { viewModel.updateStatus(
                                game.id,
                                "Want To Play"
                            )
                                viewModel.refreshGames()
                                scope.launch {
                                    snackbarHostState.showSnackbar(message = WannaPlayMessage
                                    )
                                }},
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    stringResource(R.string.btn_want_to_play),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }


                    }

                    // for games that are not released yet
                    else {
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(
                            onClick = { viewModel.updateStatus(
                                game.id,
                                "Want To Play"
                            )
                                viewModel.refreshGames()
                                      },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary
                                )
                                Spacer(Modifier.width(8.dp))
                                Text(
                                    stringResource(R.string.btn_want_to_play),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = stringResource(R.string.label_genre) + " ${game.getLocalizedGenre(currentLanguage)}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.padding(horizontal = 20.dp))

                    ExpandableDescription(description = game.getLocalizedDescription(currentLanguage),
                        currentLanguage = currentLanguage)


                    Spacer(modifier = Modifier.height(10.dp))


// shows similar games
                    if (similarList.isNotEmpty()) {
                        // existing gamesRow component
                        gamesRow(
                            categoryTitle = stringResource(R.string.cat_similar),
                            games = similarList,
                            onGameClick = { clickedGame ->
                                navController.navigate("game_detail/${clickedGame.id}")
                            },
                            snackbarHostState = snackbarHostState,

                        )
                    }

                }
            }
        }
    }
// adding game sheet part
        if (showSheet) {
            val currentLang = LocalConfiguration.current.locales[0].language
            val locale = Locale(currentLang)
            // a fresh configuration based on the current state
            val config = Configuration(LocalConfiguration.current).apply {
                setLocale(locale)
                setLayoutDirection(locale)
            }
            val context = LocalContext.current
            val localizedContext = remember(currentLang) {
                context.createConfigurationContext(config)
            }
            var isFavSelected by remember {
                mutableStateOf(game.isFavorite)
            }
            var isPlayingSelected by remember {
                mutableStateOf(game.status == "Currently Playing")
            }
            var isCompletedSelected by remember {
                mutableStateOf(game.status == "Completed") }

            val selectedStatus = when {
                isPlayingSelected -> "Currently Playing"

                isCompletedSelected -> "Completed"

                reviewText.isNotBlank() ||
                        rating > 0 ||
                        hoursPlayed.isNotBlank()
                    -> "Completed"
                else -> game.status
            }

            ModalBottomSheet(

                onDismissRequest = { showSheet = false },
                sheetState = sheetState,
               containerColor = MaterialTheme.colorScheme.onPrimary,
                scrimColor = Color.Black.copy(alpha = 0.7f),
                modifier = Modifier.pointerInput(Unit) {
                    detectTapGestures(onTap = { focusManager.clearFocus()
                    })
                }
            ) {
                CompositionLocalProvider(
                    LocalConfiguration provides config,
                    LocalLayoutDirection provides if (currentLang == "ar") LayoutDirection.Rtl else LayoutDirection.Ltr,
                    LocalContext provides localizedContext
                ){
                Column(modifier = Modifier
                    .padding(15.dp)
                    .padding(bottom = 32.dp)
                    .padding(top = 5.dp)) {
                    Text(
                        stringResource(R.string.sheet_title),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )

                    // lists Icons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,

                        ) {
                        ListIconOption(R.drawable.addtofavs, stringResource(R.string.list_favs),isSelected = isFavSelected,
                            onSelect = { isFavSelected = !isFavSelected } )
                        ListIconOption(R.drawable.gamepad, stringResource(R.string.list_playing), isSelected = isPlayingSelected,
                            onSelect = {
                                isPlayingSelected = !isPlayingSelected
                                if (isPlayingSelected) {
                                    isCompletedSelected = false
                                }
                            })
                        ListIconOption(R.drawable.finishedgame, stringResource(R.string.list_completed), isSelected = isCompletedSelected,
                            onSelect = { isCompletedSelected = !isCompletedSelected
                                if (isCompletedSelected) {
                                    isPlayingSelected = false
                                }

                            })
                    }

                    //  Star rating
                    Text(
                        stringResource(R.string.label_your_rating),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 10.dp),
                        color = MaterialTheme.colorScheme.outline
                    )
                    Row(modifier = Modifier.padding(vertical = 10.dp)) {
                        (1..5).forEach { index ->
                            Icon(
                                imageVector = if (index <= rating) Icons.Filled.Star else Icons.Outlined.Star,
                                contentDescription = "Star $index",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { rating = index },
                                tint = if (index <= rating) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                            )
                        }
                    }

                    //  log &Reviews
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        OutlinedTextField(
                            textStyle = MaterialTheme.typography.bodySmall,
                            value = reviewText,
                            onValueChange = { reviewText = it },
                            label = {
                                Text(
                                    stringResource(R.string.hint_review),
                                    color = MaterialTheme.colorScheme.secondary,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            modifier = Modifier.weight(1f),
                            minLines = 3,
                            maxLines = 6,
                        )

                        OutlinedTextField(
                            value = hoursPlayed, //  String state
                            onValueChange = { input ->
                                if (input.isEmpty() || input.toDoubleOrNull() != null || input.endsWith(
                                        "."
                                    )
                                ) {
                                    hoursPlayed = input
                                }
                            },
                            label = {
                                Text(
                                    stringResource(R.string.label_playtime),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            suffix = {
                                Text(
                                    stringResource(R.string.unit_hrs),
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            modifier = Modifier
                                .width(100.dp)
                                .wrapContentHeight(),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                            textStyle = MaterialTheme.typography.bodySmall
                        )
                    }
                    val context = LocalContext.current
                    val shareText = stringResource(R.string.shareText)
                    Button(
                        onClick = {
                            val sendIntent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, shareText)
                                type = "text/plain"
                                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null).apply {

                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        }

                            context.startActivity(shareIntent)
                        } ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onSecondaryContainer, //  background
                            contentColor = MaterialTheme.colorScheme.primary         //  text
                        ),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.share),
                                contentDescription = "share",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(15.dp))

                            Text(
                                stringResource(R.string.btn_share_journey),
                                style = MaterialTheme.typography.bodyMedium
                            )

                        }
                    }
                    Button(
                        onClick = { viewModel.toggleFavorite(game.id,
                            isFavSelected
                        )
                            // Save status
                            viewModel.updateStatus(
                                game.id,
                                selectedStatus
                            )
                            // Save review if user entered anything
                            if (
                                reviewText.isNotBlank() ||
                                rating > 0 ||
                                hoursPlayed.isNotBlank()
                            ) {
                                val review = ReviewEntity(
                                    gameId = game.id,
                                    userRating = rating.toFloat(),
                                    reviewContent = reviewText,
                                    hoursPlayed = hoursPlayed.toDoubleOrNull() ?: 0.0
                                )
                                viewModel.saveOrUpdateReview(review)
                            }
                            //refreshing dashboard
                            viewModel.refreshGames()
                            viewModel.refreshDashboard()
                            viewModel.refreshReviews()
                            showSheet= false
                            scope.launch {
                                snackbarHostState.showSnackbar(message = savedMessage
                                )
                            }

                            // Close sheet
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    ) {
                        Text(
                            stringResource(R.string.btn_save_changes),
                            color = MaterialTheme.colorScheme.surface,
                            style = MaterialTheme.typography.bodyMedium
                        )


                    }
                }
                }
            }
        }

                }



fun getSimilarGames(currentGame: GameEntity, allGames: List<GameEntity>): List<GameEntity> {
    // Current categories from the DB are a comma-separated String
    val currentCategories = currentGame.Similars.split(",").map { it.trim() }

    return allGames.filter { game ->
        // not show the game we are currently looking at
        val isNotCurrentGame = game.id != currentGame.id

        val gameCategories = game.Similars.split(",").map { it.trim() }        //  Split categories
        // Check if they share at least one category
        val hasSharedGenre = gameCategories.any { it in currentCategories }

        isNotCurrentGame && hasSharedGenre
    }.take(10) // Limit to 10 for performance
}


@Composable
fun ListIconOption(iconRes: Int, label: String, isSelected: Boolean,
                   onSelect: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick =onSelect, modifier = Modifier.background(if (isSelected) MaterialTheme.colorScheme. onTertiaryContainer
        else MaterialTheme.colorScheme.onSecondaryContainer,
            CircleShape) ) {
            Icon(painter = painterResource(id = iconRes), contentDescription = label , tint = if (isSelected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.secondary)
        }
        Text(label, style = MaterialTheme.typography.bodySmall , modifier = Modifier.padding(5.dp), color = MaterialTheme.colorScheme.outline)
    }
}
@Composable
fun ExpandableDescription(description: String?, currentLanguage: String ) {
   // Track whether it's expanded or not
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize() //  smooth
            .clickable { isExpanded = !isExpanded } //clicking the whole block
    ) {
        if (description != null) {
            Text(
                modifier = Modifier.padding(0.dp,20.dp, )

                       , text = description,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                maxLines = if (isExpanded) Int.MAX_VALUE else 6,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Text(
            text = if (isExpanded) stringResource(R.string.read_less) else stringResource(R.string.read_more),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

@Composable
fun GDTopBar( navController: NavController){
    Column(Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
        AppTopBar()

        Row(  modifier = Modifier.padding(3.dp)) {
            IconButton(
                onClick = { navController.popBackStack() },
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Back",
                    modifier =  Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            IconButton(
                onClick = { navController.navigate("home") {
                    popUpTo("home") { inclusive = true }
                }},
            ) {
                Icon(
                    painterResource(R.drawable.fort__1),
                    contentDescription = "Home",
                    modifier =  Modifier.size(20.dp),
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

}

    }


