package com.example.scrollablelistxml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.scrollablelistxml.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi Timber (dengan guard agar tidak ditanam ulang saat recreate)
        if (Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber diinisialisasi")
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}