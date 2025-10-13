package com.works.coinall.api

import com.works.coinall.model.CoinDataClass
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinGeckoApi {
    @GET("coins/markets")
    suspend fun getCoins(
        @Query("vs_currency") vsCurrency: String,
        @Query("x_cg_demo_api_key") apiKey: String
    ): List<CoinDataClass>
}