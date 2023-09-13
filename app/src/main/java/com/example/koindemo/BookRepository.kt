package com.example.koindemo

class BookRepository(val apiService: ApiService) {
    fun getAllBooks() = apiService.getBooks()
}