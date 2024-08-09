package com.books.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecommendedBookIds(
    @Json(name = "you_will_like_section")
    val ids: List<Int>
)
