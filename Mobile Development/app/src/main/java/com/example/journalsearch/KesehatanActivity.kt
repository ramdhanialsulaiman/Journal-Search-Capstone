package com.example.journalsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.journalsearch.databinding.ActivityKesehatanBinding

class KesehatanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKesehatanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKesehatanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}