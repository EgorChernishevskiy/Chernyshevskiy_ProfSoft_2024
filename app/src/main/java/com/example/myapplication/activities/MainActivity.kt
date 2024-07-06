package com.example.myapplication.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonNavigate.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("message", getString(R.string.greetings_textView_label))
            }
            startActivity(intent)
        }
    }
}