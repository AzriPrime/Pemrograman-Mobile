package com.example.scrollablelistcompose.viewmodel

import com.example.scrollablelistcompose.data.Fish
import com.example.scrollablelistcompose.data.FishData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber

class FishViewModel(private val owner: String) : ViewModel() {

    // StateFlow untuk data list ikan
    private val _fishList = MutableStateFlow<List<Fish>>(emptyList())
    val fishList: StateFlow<List<Fish>> = _fishList.asStateFlow()

    init {
        loadFishData()
    }

    /**
     * Memuat data ikan ke dalam StateFlow.
     * Timber log saat setiap item masuk ke dalam list (ketentuan d.a).
     */
    private fun loadFishData() {
        val data = FishData.getFishList()
        _fishList.value = data

        Timber.d("[$owner] Memuat ${data.size} data ikan ke dalam list")
        data.forEach { fish ->
            Timber.d("[$owner] Data item masuk: ${fish.name} (${fish.latinName})")
        }
    }

    /**
     * Mendapatkan data ikan berdasarkan index.
     */
    fun getFishByIndex(index: Int): Fish {
        return _fishList.value[index]
    }

    /**
     * Event handler saat tombol Detail ditekan (ketentuan d.b & d.c).
     * Log data ikan yang dipilih dan index tujuan.
     */
    fun onDetailClick(index: Int) {
        val fish = _fishList.value[index]
        Timber.d("[$owner] Tombol Detail ditekan untuk: ${fish.name}")
        Timber.d("[$owner] Navigasi ke halaman Detail → index=$index, data=$fish")
    }

    /**
     * Event handler saat tombol Wiki (Explicit Intent) ditekan (ketentuan d.b).
     * Log nama ikan dan URL yang akan dibuka.
     */
    fun onWikiClick(index: Int) {
        val fish = _fishList.value[index]
        Timber.d("[$owner] Tombol Wiki (Explicit Intent) ditekan untuk: ${fish.name}")
        Timber.d("[$owner] Membuka URL: ${fish.wikiUrl}")
    }
}
