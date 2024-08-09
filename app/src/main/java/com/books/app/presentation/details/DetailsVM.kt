package com.books.app.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.books.app.data.BooksRepository
import com.books.app.data.model.Book
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch


@HiltViewModel(assistedFactory = DetailsVM.DetailsVMFactory::class)
class DetailsVM @AssistedInject constructor(
    @Assisted private val bookId: Int?,
    private val repository: BooksRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState?>(null)
    val uiState = _uiState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            val data = repository.fetchDetailsData()

            if (data.books.isEmpty()) return@launch
            val books = data.books.sortedByDescending { it.id == bookId }

            _uiState.value = DetailsUiState(
                selectedBook = books.first(),
                books = persistentListOf<Book>().addAll(books),
                recommendationBooks = persistentListOf<Book>().addAll(data.recommendations)
            )
        }
    }

    fun selectBookById(bookId: Int) {
        viewModelScope.launch {
            val currState = _uiState.value ?: return@launch

            val selectedBook = with(currState) {
                books.find { it.id == bookId }
            } ?: currState.selectedBook

            _uiState.value = currState.copy(selectedBook = selectedBook)
        }
    }

    @AssistedFactory
    interface DetailsVMFactory {
        fun create(id: Int?): DetailsVM
    }
}
