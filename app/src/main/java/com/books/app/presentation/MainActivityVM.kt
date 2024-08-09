package com.books.app.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.data.BooksRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityVM @Inject constructor(
    private val repository: BooksRepository
) : ViewModel() {

    private val tag = MainActivityVM::class.simpleName.orEmpty()

    init {
        viewModelScope.launch {
            val result = repository.loadData()
            Log.d(tag, "load data: $result")
        }
    }

}