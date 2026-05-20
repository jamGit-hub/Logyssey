package com.example.logyssey.data

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.AndroidViewModel

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch
import java.util.Locale


class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val dbHelper = LogysseyDbHelper(application)
    private val repository = GameRepository(dbHelper)
    private val _allGames = MutableStateFlow<List<GameEntity>>(emptyList())
    val allGames: StateFlow<List<GameEntity>> = _allGames

    private val _reviews =
        MutableStateFlow<List<ReviewWithGame>>(emptyList())

    val reviews: StateFlow<List<ReviewWithGame>> = _reviews
    private val _dashboardData = MutableStateFlow<GameDashboard?>(null)
    val dashboardData: StateFlow<GameDashboard?> = _dashboardData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val games = repository.getAllGames()
            val dashboard = repository.getDashboardData()

            _allGames.value = games
            _dashboardData.value = dashboard
            _reviews.value = repository.getAllReviews()
        }
    }

    fun refreshReviews() {

        viewModelScope.launch(Dispatchers.IO) {

            _reviews.value =
                repository.getAllReviews()
        }
    }

    fun refreshDashboard() {
        viewModelScope.launch(Dispatchers.IO) {
            // This replaces the "combine" logic you had in Room
            _dashboardData.value = repository.getDashboardData()
        }
    }

    fun refreshGames() {
        viewModelScope.launch(Dispatchers.IO) {
            _allGames.value = repository.getAllGames()
        }
    }

    fun toggleFavorite(gameId: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteStatus(
                gameId,
                isFavorite
            )

            refreshGames()
        }
    }

    fun updateStatus(gameId: Int, newStatus: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGameStatus(gameId, newStatus)
            refreshGames() // Update UI
        }
    }

    fun insertReview(review: ReviewEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertReview(review)
            refreshDashboard()
        }
    }

    fun deleteRev(reviewId: Int) {
        viewModelScope.launch(Dispatchers.IO) {

            repository.deleteRev(reviewId)
            refreshReviews()
        }
    }


    fun getDashboardStats(): Pair<Double, Float> {
        val db = dbHelper.readableDatabase
        val cursor =
            db.rawQuery("SELECT SUM(hoursPlayed), AVG(userRating) FROM reviews_table", null)

        var totalHrs = 0.0
        var avgRate = 0.0f

        if (cursor.moveToFirst()) {
            totalHrs = cursor.getDouble(0)
            avgRate = cursor.getFloat(1)
        }
        cursor.close()
        return Pair(totalHrs, avgRate)
    }

    fun getAllReviews(): List<ReviewWithGame> {
        return repository.getAllReviews()
    }

    fun getReviewByGameId(gameId: Int): ReviewEntity? {
        return repository.getReviewByGameId(gameId)
    }


    fun saveOrUpdateReview(review: ReviewEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            val existingReview =
                repository.getReviewByGameId(review.gameId)
            // creats new if this is the first one
            if (existingReview == null) {
                repository.insertReview(review)

            } else {
                repository.updateRev(
                    review.copy(
                        reviewId = existingReview.reviewId
                    )
                )
            }

            refreshReviews()
        }
    }

}


