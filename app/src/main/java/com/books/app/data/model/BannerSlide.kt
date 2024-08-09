package com.books.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BannerSlide(
    val id: Int,
    @Json(name = "book_id")
    val bookId: Int,
    @Json(name = "cover")
    val coverUrl: String
)
