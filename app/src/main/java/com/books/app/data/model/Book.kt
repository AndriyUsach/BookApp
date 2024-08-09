package com.books.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Book(
    val id: Int,
    val name: String,
    val author: String,
    val summary: String,
    val genre: String,
    @Json(name = "cover_url")
    val coverUrl: String,
    val views: String,
    val likes: String,
    val quotes: String,
)
