package data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Fish(
    val name: String,
    val latinName: String,
    val habitat: String,
    val size: String,
    val description: String,
    val imageResId: Int,
    val wikiUrl: String
) : Parcelable