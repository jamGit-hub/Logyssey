package com.example.logyssey.screens.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier




import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.ComposeNode
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.logyssey.R
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun reviewComponent(
            gamePoster: String,
            gameTitle: String,
            review: String,
            rating: Int,
            hours: String,
            isFavorite: Boolean,
            timestamp: String,
            onEditClick: () -> Unit,
            onDeleteClick: () -> Unit,

        ) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 10.dp),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.Top
        ) {

            // poster
            AsyncImage(
                model = gamePoster,
                contentDescription = gameTitle,
                modifier = Modifier
                    .width(90.dp)
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {

                // TITLE ONLY
                Text(
                    text = gameTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                // STATS
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )

                    Text(
                        text = " $rating/5",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(14.dp))

                    Icon(
                        imageVector = Icons.Default.Schedule,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )

                    Text(
                        text = " $hours ${stringResource(R.string.unit_hrs)}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // rev
                ExpandableRev(review)

                Spacer(modifier = Modifier.height(10.dp))

                // time
                Text(
                    text = timestamp,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.surfaceTint
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // actions colunm
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,

                modifier = Modifier.height(120.dp)
            ) {

                // fav
                Icon(
                    imageVector =
                        if (isFavorite)
                            Icons.Filled.Favorite
                        else
                            Icons.Outlined.FavoriteBorder,

                    contentDescription = null,

                    tint = MaterialTheme.colorScheme.primary,

                    modifier = Modifier.size(22.dp)
                )

                // edit
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.size(26.dp)
                ) {

                    Icon(
                        painter = painterResource(R.drawable.magic_wand),
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }

                // delete
                IconButton(
                    onClick = {
                        onDeleteClick() },
                    modifier = Modifier.size(26.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",

                        tint = MaterialTheme.colorScheme.error,

                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }


}



@Composable
fun ExpandableRev(review: String?) {
    var isExpanded by remember { mutableStateOf(false) }
    //  if the text is actually long enough to require a "Read More" button
    var isClickable by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .then(
                if (isClickable) Modifier.clickable { isExpanded = !isExpanded }
                else Modifier
            )
    ) {
        if (review != null) {
            Text(
                modifier = Modifier.padding(horizontal = 0.dp, vertical = 20.dp),
                text = review,
                color = MaterialTheme.colorScheme.surfaceTint,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                maxLines = if (isExpanded) Int.MAX_VALUE else 3,
                overflow = TextOverflow.Ellipsis,
                onTextLayout = { textLayoutResult ->
                    // Check if the text is longer than 3 lines OR has already been truncated
                    if (textLayoutResult.lineCount > 3 || textLayoutResult.hasVisualOverflow) {
                        isClickable = true
                    }
                }
            )
        }
        if (isClickable) {
            Text(
                text = if (isExpanded) stringResource(R.string.read_less)
                else stringResource(R.string.read_more),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}


fun formatTimestamp(timestamp: Long): String {

    val formatter = SimpleDateFormat(
        "MMM d, yyy • h:mm a",
        Locale.getDefault()
    )
    return formatter.format(Date(timestamp))
}