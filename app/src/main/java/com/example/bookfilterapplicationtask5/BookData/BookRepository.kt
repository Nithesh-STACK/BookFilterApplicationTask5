package com.example.bookfilterapplicationtask5

import androidx.lifecycle.LiveData

class BookRepository(private val bookDao: BookDao) {

    val readAllBooks:LiveData<List<Book>> = bookDao.getBooks()
    fun getBooksByauthor(author:String):LiveData<List<Book>>
    {
       return bookDao.getAllBooks(author)
    }

    fun addBook(book: Book)
    {
        bookDao.addBook(book)
    }

    fun addAuthor(author: Author)
    {
        bookDao.addAuthor(author)
    }
}