package com.works.coinall.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.works.coinall.R
import com.works.coinall.components.AppHeader
import com.works.coinall.components.CoinListComponent
import com.works.coinall.view_model.FavoritesViewModel

@SuppressLint("DefaultLocale")
@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel = viewModel(),
    onNavigateToLiveDataScreen: () -> Unit,
    onNavigateToCoinDetailScreen: (String) -> Unit,
    onBack: () -> Unit
) {
    BackHandler {
        onBack()
    }
    Column(
        modifier = modifier.fillMaxSize().background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader(
            title = "CoinAll.",
            subtitle = "Favorites"
        )
        if (viewModel.favorites.isEmpty()) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(bottom = 100.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painterResource(R.drawable.warning2),
                    contentDescription = "warning",
                    Modifier.size(120.dp, 120.dp)
                )
                Spacer(modifier = Modifier.padding(7.dp))
                Text(
                    text = "There are no favorite coins",
                    color = colorResource(R.color.egg_toast),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 10.dp, start = 20.dp, end = 10.dp, bottom = 160.dp)
            ) {
                items(viewModel.favorites) { coin ->
                    CoinListComponent(
                        modifier = Modifier,
                        coin = coin,
                        onNavigateToCoinDetailScreen = { onNavigateToCoinDetailScreen(coin.id) },
                        viewModel2 = viewModel,
                    )
                }
            }

        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 100.dp)
    ) {
        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.egg_toast),
                contentColor = Color.Black,
            ),
            onClick = onNavigateToLiveDataScreen
        ) {
            Text(
                text = "+   Add Coin to Favorites",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}