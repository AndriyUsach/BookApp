package com.books.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookList(
    @Json(name = "books")
    val books: List<Book>
)
