package com.silencefly96.camerademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.silencefly96.camerademo.databinding.ActivityCustomBinding

class CustomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}