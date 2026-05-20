package com.example.logyssey.data



data class GameEntity(
    val id: Int,
    val isUpcoming: Boolean,
    val title: String,
    val Poster: String,
    val releaseDate: Long,
    val description: String,
    val descriptionAR: String,
    val PSrating: Float,
    val Steamrating: Float,
    val Genre: String,
    val GenreAR: String,      // New
    val Similars: String,
    val status: String = "None",
    val isFavorite: Boolean = false
)

data class ReviewEntity(
    val reviewId: Int = 0,
    val gameId: Int,
    val userRating: Float,
    val reviewContent: String,
    val hoursPlayed: Double,
    val timestamp: Long = System.currentTimeMillis()
)

data class ReviewWithGame(
    val reviewId: Int,
    val gameTitle: String,
    val gamePoster: String,
    val reviewContent: String,
    val userRating: Float,
    val hoursPlayed: Double,
    val timestamp: Long
)


data class GameDashboard(
    val latestReleases: List<GameEntity> = emptyList(),
    val popularGames: List<GameEntity> = emptyList(),
    val upcomingGames: List<GameEntity> = emptyList()
)

fun GameEntity.getLocalizedGenre(lang: String): String {
    return if (lang == "ar") GenreAR else Genre
}

fun GameEntity.getLocalizedDescription(lang: String): String {
    return if (lang == "ar") descriptionAR else description
}


