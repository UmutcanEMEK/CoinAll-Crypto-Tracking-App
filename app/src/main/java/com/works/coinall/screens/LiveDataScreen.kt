package com.works.coinall.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.works.coinall.view_model.CoinViewModel
import com.works.coinall.R
import com.works.coinall.components.AppHeader
import com.works.coinall.components.CoinListComponent
import com.works.coinall.view_model.FavoritesViewModel


@SuppressLint("DefaultLocale")
@Composable
fun LiveDataScreen(
    modifier: Modifier = Modifier,
    viewModel: CoinViewModel = viewModel(),
    viewModel2: FavoritesViewModel = viewModel(),
    onNavigateToCoinDetailScreen: (String) -> Unit,
    onBack: () -> Unit
) {
    BackHandler {
        onBack()
    }
    val coins by viewModel.coins.collectAsState()
    Column(
        modifier = modifier.fillMaxSize().background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AppHeader(
            title = "CoinAll.",
            subtitle = "Live Data"
        )

        var text by remember { mutableStateOf("") }

        val filteredCoins = if (text.isEmpty()) {
            coins
        } else {
            coins.filter { coin ->
                coin.name.contains(text, ignoreCase = true) ||
                        coin.symbol.contains(text, ignoreCase = true)
            }
        }
        TextField(
            modifier = Modifier
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(80.dp)),
            value = text,
            onValueChange = { newText -> text = newText },
            placeholder = {
                Text(
                    text = "Search Cryptocurrency",
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(R.color.santas_gray),
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = colorResource(R.color.gluon_gray),
                unfocusedContainerColor = colorResource(R.color.gluon_gray),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = colorResource(R.color.egg_toast)
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.loupe),
                    contentDescription = "search icon",
                    tint = colorResource(R.color.santas_gray),
                    modifier = Modifier
                        .size(30.dp)
                        .padding(start = 5.dp)
                )
            }

        )
        LazyColumn(
            modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 10.dp, bottom = 100.dp)
        ) {
            items(filteredCoins) { coin ->
                CoinListComponent(
                    modifier = Modifier,
                    coin = coin,
                    onNavigateToCoinDetailScreen = { onNavigateToCoinDetailScreen(coin.id) },
                    viewModel2 = viewModel2,
                )
            }
        }
    }
}



