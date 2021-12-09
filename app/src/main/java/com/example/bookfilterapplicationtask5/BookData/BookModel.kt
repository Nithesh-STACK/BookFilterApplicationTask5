package com.example.bookfilterapplicationtask5

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookModel(application: Application): AndroidViewModel(application) {

    private val repository:BookRepository
    val readAllBooks:LiveData<List<Book>>
    init {
        val bookDao = BookDatabase.getDatabase(application).bookDao()
        repository= BookRepository(bookDao)
        readAllBooks=bookDao.getBooks()
    }
    fun getBooksByAuthor(author:String):LiveData<List<Book>>
    {
        return repository.getBooksByauthor(author)
    }

    fun addBook(book: Book)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addBook(book)
        }
    }
    fun addAuthor(author: Author)
    {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAuthor(author)
        }
    }
}