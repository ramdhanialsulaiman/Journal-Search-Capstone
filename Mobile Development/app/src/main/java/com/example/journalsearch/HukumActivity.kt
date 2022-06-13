package com.example.journalsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.journalsearch.databinding.ActivityHukumBinding

class HukumActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHukumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHukumBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}