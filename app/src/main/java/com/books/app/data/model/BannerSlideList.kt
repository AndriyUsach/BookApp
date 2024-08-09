package com.books.app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BannerSlideList(
    @Json(name = "top_banner_slides")
    val slides: List<BannerSlide>
)

