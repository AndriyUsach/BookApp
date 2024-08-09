package com.books.app.data.model


data class HomeData(
    val books: List<Book> = emptyList(),
    val topBannerSlides: List<BannerSlide> = emptyList()
)
