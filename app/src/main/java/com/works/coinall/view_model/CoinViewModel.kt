package com.works.coinall.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.works.coinall.model.CoinDataClass
import com.works.coinall.api.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CoinViewModel : ViewModel() {

    private val _coins = MutableStateFlow<List<CoinDataClass>>(emptyList())
    val coins: StateFlow<List<CoinDataClass>> = _coins

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = ApiClient.api.getCoins(
                    vsCurrency = "usd",
                    apiKey = ApiClient.getApiKey()
                )
                _coins.value = result
                Log.d("API_TEST", "Coins fetched: $result")
            } catch (e: Exception) {
                Log.e("API_TEST", "Error: ${e.message}")
            }
        }
    }
}