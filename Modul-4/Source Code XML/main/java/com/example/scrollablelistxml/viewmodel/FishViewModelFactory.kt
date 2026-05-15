package com.example.scrollablelistxml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory untuk membuat FishViewModel dengan parameter String.
 * Diperlukan karena ViewModel secara default tidak bisa menerima
 * parameter custom di constructornya.
 *
 * @param title Parameter bertipe String yang akan diteruskan ke FishViewModel.
 */
class FishViewModelFactory(private val title: String) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FishViewModel::class.java)) {
            return FishViewModel(title) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}