package com.example.unlimintjokes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unlimintjokes.databinding.ActivityJokesBinding

class JokesActivity : AppCompatActivity() {
    lateinit var binding: ActivityJokesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJokesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
