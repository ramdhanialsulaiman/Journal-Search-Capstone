package com.example.journalsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.journalsearch.databinding.ActivityTeknikBinding

class TeknikActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeknikBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeknikBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}