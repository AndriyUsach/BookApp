package com.books.app.di

import com.books.app.data.BooksRepository
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Singleton
    @Provides
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        return Firebase.remoteConfig
    }

    @Provides
    fun provideBooksRepository(
        remoteConfig: FirebaseRemoteConfig,
        moshi: Moshi
    ): BooksRepository {

        return BooksRepository(remoteConfig, moshi)

    }

}