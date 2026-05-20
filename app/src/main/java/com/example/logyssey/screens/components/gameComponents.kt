package com.example.logyssey.screens.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.logyssey.data.GameViewModel

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.logyssey.R

import com.example.logyssey.data.GameEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun GameCard(game: GameEntity, onClick: () -> Unit,  viewModel: GameViewModel,
             snackbarHostState: SnackbarHostState,    scope: CoroutineScope
) {
    val WannaPlayMessage = stringResource(R.string.dashboard_wanna_play)
    val scope = rememberCoroutineScope()
    androidx.compose.material3.Card(
        modifier =Modifier
            .width(160.dp)
            .height(250.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column {
            // Image
            AsyncImage(
                model = game.Poster,
                contentDescription = " ${game.title} Poster",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp),
                contentScale = ContentScale.Crop
            )

            // Text
            Column(modifier = Modifier
                .padding(10.dp ,10.dp , 10.dp , 0.dp)
                .weight(1f)) {
                Text(
                    color = MaterialTheme.colorScheme.onBackground,
                    text = game.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp ,15.dp , 10.dp , 0.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(modifier = Modifier.padding (0.dp)) {
                if (game.isUpcoming) {
                    TextButton(
                        onClick = {  viewModel.updateStatus(game.id, "Want To Play")
                            viewModel.refreshGames()
                            viewModel.refreshDashboard()

                            scope.launch {
                                snackbarHostState.showSnackbar(message = WannaPlayMessage
                                )
                            }},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.padding(10.dp,5.dp),
                            text = stringResource(R.string.btn_want_to_play),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                } else {
                    Text(
                        modifier = Modifier.padding(15.dp,10.dp),
                        text = "★ ${"%.2f".format((game.PSrating + game.Steamrating) / 2)}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.outline
                    )
                }
            }
        }
    }
}

