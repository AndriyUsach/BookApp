package com.books.app.presentation.util

import com.books.app.data.model.BannerSlide
import com.books.app.data.model.Book

object PreviewData {

    fun getBook(): Book {
        return getBook(0)
    }

    fun getBookList(size: Int): List<Book> {
        return List(size) { getBook(it) }
    }

    fun getBannerSlideList(size: Int): List<BannerSlide> {
        return List(size) {
            BannerSlide(it, it, "https://unsplash.it/600/300")
        }
    }

    private fun getBook(id: Int) = Book(
        id = id,
        name = "A Beta Before an Alpha HangryWolf WithHis Queen",
        author = "author$id",
        summary = "According to researchers at Duke University, habits account for about 40 percent of our behaviors on any given day. Your life today is essentially the sum of your habits. How in shape or out of shape you are? A result of your habits. How happy or unhappy you are? A result of your habits. How successful or unsuccessful you are? A result of your habits.",
        genre = "Romance$id",
        coverUrl = "https://unsplash.it/600/300",
        views = "400K",
        likes = "20K",
        quotes = "10K"
    )

}