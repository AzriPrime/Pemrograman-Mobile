package com.example.scrollablelistcompose.data

import androidx.annotation.DrawableRes

data class Fish(
    val name: String,
    val latinName: String,
    val habitat: String,
    val size: String,
    val description: String,
    @DrawableRes val imageResId: Int,
    val wikiUrl: String
)
