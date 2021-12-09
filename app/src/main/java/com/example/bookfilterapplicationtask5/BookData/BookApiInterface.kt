package com.example.bookfilterapplicationtask5

import retrofit2.http.GET

interface BookApiInterface {
    @GET("books")
    suspend fun getAllBooks():List<AllBooksData>
}