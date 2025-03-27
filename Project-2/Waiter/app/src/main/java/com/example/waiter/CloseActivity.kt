package com.example.waiter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.waiter.databinding.ActivityMainLrBinding

class CloseActivity : AppCompatActivity() {
    private var binding: ActivityMainLrBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainLrBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        supportActionBar!!.hide()
        finish()
    }
}