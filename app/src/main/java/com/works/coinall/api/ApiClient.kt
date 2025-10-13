package com.works.coinall.api

import com.works.coinall.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    private const val BASE_URL = "https://api.coingecko.com/api/v3/"
    val API_KEY: String = BuildConfig.COINGECKO_API_KEY

    val api: CoinGeckoApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinGeckoApi::class.java)
    }

    fun getApiKey(): String = API_KEY
}
