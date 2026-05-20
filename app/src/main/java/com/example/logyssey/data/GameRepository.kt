package com.example.logyssey.data

import android.content.ContentValues
import android.database.Cursor


class GameRepository(private val dbHelper: LogysseyDbHelper) {

    fun getAllGames(): List<GameEntity> {
        val list = mutableListOf<GameEntity>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM games_table", null)
        if (cursor.moveToFirst()) {
            do {
                list.add(cursorToGame(cursor))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun updateGameStatus(gameId: Int, newStatus: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply { put("status", newStatus) }
        db.update("games_table", values, "id = ?", arrayOf(gameId.toString()))
    }

    fun updateFavoriteStatus(gameId: Int, isFav: Boolean) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply { put("isFavorite", if (isFav) 1 else 0) }
        db.update("games_table", values, "id = ?", arrayOf(gameId.toString()))
    }

    // Maps a DB Row back to GameEntity class
    private fun cursorToGame(cursor: Cursor): GameEntity {
        return GameEntity(
            id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            isUpcoming = cursor.getInt(cursor.getColumnIndexOrThrow("isUpcoming")) == 1,
            title = cursor.getString(cursor.getColumnIndexOrThrow("title")),
            Poster = cursor.getString(cursor.getColumnIndexOrThrow("Poster")),
            releaseDate = cursor.getLong(cursor.getColumnIndexOrThrow("releaseDate")),
            description = cursor.getString(cursor.getColumnIndexOrThrow("description")),
            descriptionAR = cursor.getString(cursor.getColumnIndexOrThrow("descriptionAR")),
            PSrating = cursor.getFloat(cursor.getColumnIndexOrThrow("PSrating")),
            Steamrating = cursor.getFloat(cursor.getColumnIndexOrThrow("Steamrating")),
            Genre = cursor.getString(cursor.getColumnIndexOrThrow("Genre")),
            GenreAR = cursor.getString(cursor.getColumnIndexOrThrow("GenreAR")),
            Similars = cursor.getString(cursor.getColumnIndexOrThrow("Similars")),
            status = cursor.getString(cursor.getColumnIndexOrThrow("status")),
            isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow("isFavorite")) == 1
        )
    }

    fun getDashboardData(): GameDashboard {
        val db = dbHelper.readableDatabase
        // Latest Releases: isUpcoming = 0, sorted by date
        val latest =
            fetchGamesByQuery("SELECT * FROM games_table WHERE isUpcoming = 0 ORDER BY releaseDate DESC LIMIT 10")
        // Popular Games: Sorted by average of PS and Steam ratings
        val popular =
            fetchGamesByQuery("SELECT * FROM games_table ORDER BY (PSrating + Steamrating) / 2 DESC LIMIT 10")
        // Upcoming Games: isUpcoming = 1
        val upcoming =
            fetchGamesByQuery("SELECT * FROM games_table WHERE isUpcoming = 1 ORDER BY releaseDate ASC")
        return GameDashboard(latest, popular, upcoming)
    }

    // Helper to avoid repeating cursor logic
    private fun fetchGamesByQuery(sql: String): List<GameEntity> {
        val list = mutableListOf<GameEntity>()
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(sql, null)
        if (cursor.moveToFirst()) {
            do {
                list.add(cursorToGame(cursor))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun insertReview(review: ReviewEntity) {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("gameId", review.gameId)
            put("userRating", review.userRating)
            put("reviewContent", review.reviewContent)
            put("hoursPlayed", review.hoursPlayed)
            put("timestamp", review.timestamp)
        }

        db.insert("reviews_table", null, values)
    }

    fun getAllReviews(): List<ReviewWithGame> {
        val reviews = mutableListOf<ReviewWithGame>()
        val db = dbHelper.readableDatabase

        val query = """
        SELECT 
            r.reviewId,
            r.reviewContent,
            r.userRating,
            r.hoursPlayed,
            r.timestamp,
            g.title,
            g.Poster
        FROM reviews_table r
        INNER JOIN games_table g
        ON r.gameId = g.id
        ORDER BY r.timestamp DESC
    """

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {

                reviews.add(
                    ReviewWithGame(
                        reviewId = cursor.getInt(0),
                        reviewContent = cursor.getString(1),
                        userRating = cursor.getFloat(2),
                        hoursPlayed = cursor.getDouble(3),
                        timestamp = cursor.getLong(4),
                        gameTitle = cursor.getString(5),
                        gamePoster = cursor.getString(6)
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        return reviews
    }

    fun getGamesByStatus(status: String): List<GameEntity> {
        return fetchGamesByQuery(
            "SELECT * FROM games_table WHERE status = '$status'"
        )
    }
    fun getFavoriteGames(): List<GameEntity> {
        return fetchGamesByQuery(
            "SELECT * FROM games_table WHERE isFavorite = 1"
        )
    }

    fun deleteRev(reviewId: Int){
        val db = dbHelper.writableDatabase
        db.delete(
            "reviews_table",
            "reviewId = ?",
            arrayOf(reviewId.toString())
        )


    }



    //to checkif a game has a review or not

    fun getReviewByGameId(gameId: Int): ReviewEntity? {
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM reviews_table WHERE gameId = ? LIMIT 1",
            arrayOf(gameId.toString())
        )

        var review: ReviewEntity? = null

        if (cursor.moveToFirst()) {

            review = ReviewEntity(
                reviewId = cursor.getInt(cursor.getColumnIndexOrThrow("reviewId")),
                gameId = cursor.getInt(cursor.getColumnIndexOrThrow("gameId")),
                userRating = cursor.getFloat(cursor.getColumnIndexOrThrow("userRating")),
                reviewContent = cursor.getString(
                    cursor.getColumnIndexOrThrow("reviewContent")),
                hoursPlayed = cursor.getDouble(cursor.getColumnIndexOrThrow("hoursPlayed")
                ),
                 timestamp = cursor.getLong(cursor.getColumnIndexOrThrow("timestamp"))
            )
        }

        cursor.close()
        return review
    }

//the update logic
    fun updateRev(review: ReviewEntity){

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("userRating", review.userRating)
            put("reviewContent", review.reviewContent)
            put("hoursPlayed", review.hoursPlayed)
            put("timestamp", System.currentTimeMillis())
        }

        db.update(
            "reviews_table",
            values,
            "reviewId = ?",
            arrayOf(review.reviewId.toString())
        )

    }



}

