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
                val allCoins = mutableListOf<CoinDataClass>()
                var currentPage = 1
                val perPage = 250
                var fetched: List<CoinDataClass>

                do {
                    fetched = ApiClient.api.getCoins(
                        vsCurrency = "usd",
                        perPage = perPage,
                        page = currentPage,
                        apiKey = ApiClient.getApiKey()
                    )

                    Log.d("API_TEST", "Page $currentPage -> ${fetched.size} coins")
                    if (fetched.isNotEmpty()) {
                        allCoins.addAll(fetched)
                        currentPage++
                    }

                } while (fetched.isNotEmpty() && currentPage <= 6)

                _coins.value = allCoins
                Log.d("API_TEST", "Total coins fetched: ${allCoins.size}")

            } catch (e: Exception) {
                Log.e("API_TEST", "Error: ${e.message}")
            }
        }
    }
}