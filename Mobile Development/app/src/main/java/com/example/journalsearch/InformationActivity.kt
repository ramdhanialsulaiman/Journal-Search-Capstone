package com.example.journalsearch


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.journalsearch.databinding.ActivityInformationBinding


class InformationActivity : AppCompatActivity() {
    private lateinit var binding:ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}