package com.example.bookfilterapplicationtask5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var BookViewModelData:BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BookViewModelData = ViewModelProvider(this).get(BookViewModel::class.java)
        findViewById<TextView>(R.id.insert).setOnClickListener()
        {
            insertDataToDatabase()
        }
        val adapter = BooksAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager= LinearLayoutManager(applicationContext)

        val author=findViewById<TextInputLayout>(R.id.filterAuthor)?.editText?.text

        findViewById<Button>(R.id.button).setOnClickListener()
        {
            findViewById<TextView>(R.id.id_title).text="Id"
            findViewById<TextView>(R.id.title_title).text="Book Title"
            findViewById<TextView>(R.id.year_title).text="Year"
            BookViewModelData.getBooksByAuthor(author.toString()).observe(this,{ book->
                adapter.setData(book)
            })
        }
    }

    private fun insertDataToDatabase() {
        val bookApplication=application as BookApplication
        val bookService=bookApplication.books

        CoroutineScope(Dispatchers.IO).launch {
            val decodedBook = bookService.getAllbooks()
            withContext(Dispatchers.Main)
            {
                for(myData in decodedBook) {
                    val book =
                        Book(0,myData.title,myData.author,myData.language,myData.year.toInt(),myData.pages.toInt())
                    val author= Author(0,myData.author,myData.country)
                    BookViewModelData.addAuthor(author)
                    BookViewModelData.addBook(book)
                }
            }
        }
    }
}