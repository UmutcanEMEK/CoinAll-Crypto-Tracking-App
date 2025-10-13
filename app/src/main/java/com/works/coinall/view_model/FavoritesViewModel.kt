package com.works.coinall.view_model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.works.coinall.model.CoinDataClass

class FavoritesViewModel : ViewModel() {
    private val _favorites = mutableStateListOf<CoinDataClass>()
    val favorites: List<CoinDataClass> = _favorites

    fun toggleFavorite(coin: CoinDataClass) {
        if (_favorites.any { it.id == coin.id }) {
            _favorites.removeAll { it.id == coin.id }
        } else {
            _favorites.add(coin)
        }
    }
}