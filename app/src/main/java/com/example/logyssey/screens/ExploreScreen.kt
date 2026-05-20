package com.example.logyssey.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search


import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

import androidx.compose.ui.unit.dp

import androidx.navigation.NavController

import com.example.logyssey.data.GameViewModel
import com.example.logyssey.screens.components.AppTopBar
import com.example.logyssey.screens.components.GameCard
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Query
import com.example.logyssey.R




@OptIn(ExperimentalFoundationApi::class) // Required for stickyHeader
@Composable
fun exploreScreen(viewModel: GameViewModel, navController: NavController) {
    val allGames by viewModel.allGames.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val filteredGames = remember(searchQuery, allGames) {
        allGames.filter { it.title.contains(searchQuery, ignoreCase = true) }
    }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()


    Scaffold(
        topBar = { exploreTopbar(searchQuery = searchQuery,
            onQueryChange = { searchQuery = it }
        )
                 },
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus() // This hides the keyboard and removes cursor
            })
        }
    ) { innerPadding ->
        LazyVerticalGrid(

            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding).pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            contentPadding = PaddingValues(bottom = 5.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

                    items(filteredGames) { game ->
                        GameCard(
                            game = game,
                            onClick = { navController.navigate("game_detail/${game.id}") },
                            viewModel = viewModel,
                            snackbarHostState = snackbarHostState,
                            scope = scope
                        )



            }
        }
    }
}

@Composable
fun exploreTopbar(searchQuery: String ,  onQueryChange: (String) -> Unit) {
    Column(Modifier.background(MaterialTheme.colorScheme.onPrimary)) {
        AppTopBar()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(

                stringResource(R.string.label_explore),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(start = 8.dp),
                color = MaterialTheme.colorScheme.primary
            )

            OutlinedTextField(
                value = searchQuery,
         onValueChange = onQueryChange,
                placeholder = {
                    Text(stringResource(R.string.hint_searchGames), style = MaterialTheme.typography.bodySmall)
                },
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                singleLine = true,
                textStyle = MaterialTheme.typography.bodySmall,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedContainerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}



