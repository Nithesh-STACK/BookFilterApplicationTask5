package com.example.bookfilterapplicationtask5

import android.app.Application
import com.fasterxml.jackson.databind.ObjectMapper
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class BookApplication:Application() {
    public lateinit var books:BookApiInterface

    override fun onCreate() {
        super.onCreate()
        books=bookService()
    }
    private fun bookService():BookApiInterface
    {
        val retrofit= Retrofit.Builder()
            .baseUrl("https://httpapibooks.mocklab.io/")
            .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
            .build()
        return retrofit.create(BookApiInterface::class.java)
    }
}