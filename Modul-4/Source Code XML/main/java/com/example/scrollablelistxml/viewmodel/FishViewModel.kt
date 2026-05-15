package com.example.scrollablelistxml.viewmodel

import androidx.lifecycle.ViewModel
import com.example.scrollablelistxml.data.Fish
import com.example.scrollablelistxml.data.FishData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

/**
 * ViewModel untuk mengelola data list ikan dan event klik.
 * Menerima parameter [title] bertipe String dari ViewModelFactory.
 */
class FishViewModel(val title: String) : ViewModel() {

    private val _fishList = MutableStateFlow<List<Fish>>(emptyList())
    val fishList: StateFlow<List<Fish>> = _fishList.asStateFlow()

    private val _navigateToDetail = MutableStateFlow<Fish?>(null)
    val navigateToDetail: StateFlow<Fish?> = _navigateToDetail.asStateFlow()

    private val _openWikiUrl = MutableStateFlow<String?>(null)
    val openWikiUrl: StateFlow<String?> = _openWikiUrl.asStateFlow()

    init {
        loadFishData()
    }
    private fun loadFishData() {
        val data = FishData.getFishList()
        _fishList.value = data

        Timber.d("Data loaded: %d fish items (source: %s)", data.size, title)
        data.forEach { fish ->
            Timber.d("  → %s (%s)", fish.name, fish.latinName)
        }
    }

    fun onDetailClick(fish: Fish) {
        Timber.i("Detail button clicked: %s (%s)", fish.name, fish.latinName)
        _navigateToDetail.value = fish
    }

    fun onWikiClick(fish: Fish) {
        Timber.i("Wiki button clicked: %s → %s", fish.name, fish.wikiUrl)
        _openWikiUrl.value = fish.wikiUrl
    }

    fun onDetailNavigated() {
        _navigateToDetail.value = null
    }

    fun onWikiOpened() {
        _openWikiUrl.value = null
    }
}