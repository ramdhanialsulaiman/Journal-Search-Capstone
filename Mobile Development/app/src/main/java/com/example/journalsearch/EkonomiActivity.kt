package com.example.journalsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.journalsearch.databinding.ActivityEkonomiBinding

class EkonomiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEkonomiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEkonomiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}