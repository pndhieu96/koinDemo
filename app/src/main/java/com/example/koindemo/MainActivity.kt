package com.example.koindemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val bookViewModel: BookViewModel by viewModel()
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv = this.findViewById(R.id.tv)

        bookViewModel.books.observe(this, Observer {
            // handle UI
            tv.text = it
        })

        bookViewModel.isLoading.observe(this, Observer {
            // handle UI
        })
    }
}