package com.works.coinall.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.works.coinall.extensions.formatPrice
import com.works.coinall.model.CoinDataClass
import com.works.coinall.view_model.FavoritesViewModel

@Composable
fun CoinListComponent(
    modifier: Modifier,
    onNavigateToCoinDetailScreen: (String) -> Unit,
    viewModel2: FavoritesViewModel,
    coin: CoinDataClass

) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable { onNavigateToCoinDetailScreen(coin.id) },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            model = coin.image,
            contentDescription = coin.name,
            modifier = modifier.size(39.dp)
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp)
        ) {
            Text(
                text = coin.name,
                color = androidx.compose.ui.graphics.Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = coin.symbol.uppercase(),
                color = colorResource(com.works.coinall.R.color.santas_gray),
                fontSize = 17.sp
            )
        }
        Column(modifier = Modifier.padding(end = 10.dp)) {
            Text(
                modifier = Modifier.align(alignment = Alignment.End),
                text = "$${coin.currentPrice.formatPrice()}",
                color = androidx.compose.ui.graphics.Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.align(Alignment.End),
                text = String.format("%.2f%%", coin.priceChangePercentage24h),
                color = if (coin.priceChangePercentage24h >= 0) {
                    colorResource(com.works.coinall.R.color.vegan_mastermind)
                } else {
                    colorResource(com.works.coinall.R.color.glowing_brake_disc)
                }
            )
        }

        IconButton(
            onClick = { viewModel2.toggleFavorite(coin) },
            modifier = Modifier
                .size(40.dp)
                .padding(top = 4.dp, end = 10.dp)
        ) {
            val isFav = viewModel2.favorites.any { it.id == coin.id }
            Icon(
                painter = painterResource(com.works.coinall.R.drawable.favorites),
                contentDescription = "Favorite",
                tint = if (isFav) {
                    colorResource(com.works.coinall.R.color.egg_toast)
                } else {
                    colorResource(com.works.coinall.R.color.santas_gray)
                }
            )
        }
    }
}