package com.example.scrollablelistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.scrollablelistcompose.navigation.FishApp
import com.example.scrollablelistcompose.ui.theme.ScrollableListComposeTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi Timber (dengan guard agar tidak ditanam ulang saat recreate)
        if (Timber.treeCount == 0) {
            Timber.plant(Timber.DebugTree())
            Timber.d("Timber diinisialisasi")
        }

        enableEdgeToEdge()
        setContent {
            ScrollableListComposeTheme(dynamicColor = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FishApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}