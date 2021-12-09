package com.example.bookfilterapplicationtask5

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addAuthor(author: Author)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addBook(book: Book)

    @Query("SELECT * FROM books where author=:author limit 3")
    fun getAllBooks(author:String):LiveData<List<Book>>

    @Query("Select * from books")
    fun getBooks():LiveData<List<Book>>
}