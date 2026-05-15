package com.example.scrollablelistcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory untuk membuat FishViewModel dengan parameter String (owner).
 * Diperlukan karena ViewModel dengan parameter custom tidak bisa
 * dibuat secara otomatis oleh Android.
 */
class FishViewModelFactory(private val owner: String) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FishViewModel::class.java)) {
            return FishViewModel(owner) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
