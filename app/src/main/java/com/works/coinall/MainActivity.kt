package com.works.coinall

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.works.coinall.components.BottomNavigationBar
import com.works.coinall.components.bottomBarItems
import com.works.coinall.screens.CoinDetailScreen
import com.works.coinall.screens.FavoriteScreen
import com.works.coinall.screens.LiveDataScreen
import com.works.coinall.ui.theme.CoinAllTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoinAllTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(modifier: Modifier = Modifier) {

    val items = bottomBarItems()
    var selectedIndex by remember { mutableStateOf(0) }
    var selectedCoinId by remember { mutableStateOf<String?>(null) }
    var previousIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = items,
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it }
            )
        }
    ) { innerPadding ->

        when (selectedIndex) {
            0 -> LiveDataScreen(
                onNavigateToCoinDetailScreen = { coinId ->
                    previousIndex = selectedIndex
                    selectedCoinId = coinId
                    selectedIndex = 2
                },
                onBack = { selectedIndex = 0 },
                isLoading = true
            )
            1 -> FavoriteScreen(
                onNavigateToLiveDataScreen = { selectedIndex = 0 },
                onNavigateToCoinDetailScreen = { coinId ->
                    previousIndex = selectedIndex
                    selectedCoinId = coinId
                    selectedIndex = 2
                },
                onBack = { selectedIndex = 0 }
            )
            2 -> selectedCoinId?.let { id ->
                CoinDetailScreen(coinId = id,
                    onBack = { selectedIndex = previousIndex }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoinAllTheme {
        MyApp()
    }
}
