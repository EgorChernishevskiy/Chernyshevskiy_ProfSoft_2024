package com.example.myapplication.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySecondBinding


class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val message = intent.getStringExtra("message")

        binding.buttonShowNotification.setOnClickListener {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}