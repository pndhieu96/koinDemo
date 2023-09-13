package com.example.koindemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookViewModel(private val bookRepository: BookRepository) : ViewModel(), Callback<ResponseBody> {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _books= MutableLiveData<String>()
    val books: LiveData<String>
        get() = _books

    init {
        fetchData()
    }

    private fun fetchData() {
        _isLoading.value = true
        bookRepository.getAllBooks().enqueue(this)
    }

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        if (response.isSuccessful) {
            _books.value = response.body()?.string()
        } else {
            // handle error
        }
        _isLoading.value = false
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        _isLoading.value = false
    }
}