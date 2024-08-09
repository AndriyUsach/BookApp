package com.books.app.data

import com.books.app.data.model.BannerSlideList
import com.books.app.data.model.BookList
import com.books.app.data.model.DetailsData
import com.books.app.data.model.HomeData
import com.books.app.data.model.RecommendedBookIds
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.squareup.moshi.Moshi
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class BooksRepository(
    private val remoteConfig: FirebaseRemoteConfig,
    private val moshi: Moshi,
) {

    suspend fun loadData() = suspendCoroutine<Boolean> { continuation ->
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                continuation.resume(task.isSuccessful)
            }
    }

    fun fetchHomeData(): HomeData {
        return remoteConfig.all["json_data"]?.let { firebaseValue ->
            val json = firebaseValue.asString()

            val booksAdapter = moshi.adapter(BookList::class.java)
            val booksList = booksAdapter.fromJson(json)?.books.orEmpty()

            val slidesAdapter = moshi.adapter(BannerSlideList::class.java)
            val slides = slidesAdapter.fromJson(json)?.slides.orEmpty()

            HomeData(
                books = booksList,
                topBannerSlides = slides
            )
        } ?: HomeData()
    }

    fun fetchDetailsData(): DetailsData {
        val books = remoteConfig.all["details_carousel"]?.let { firebaseValue ->
            val json = firebaseValue.asString()

            val booksAdapter = moshi.adapter(BookList::class.java)
            booksAdapter.fromJson(json)?.books
        }.orEmpty()

        val ids = remoteConfig.all["json_data"]?.let { firebaseValue ->
            val json = firebaseValue.asString()

            val idsAdapter = moshi.adapter(RecommendedBookIds::class.java)
            idsAdapter.fromJson(json)?.ids
        }.orEmpty()

        val recommendations = books.filter { book ->
            ids.contains(book.id)
        }

        return DetailsData(
            books = books,
            recommendations = recommendations
        )
    }

}









