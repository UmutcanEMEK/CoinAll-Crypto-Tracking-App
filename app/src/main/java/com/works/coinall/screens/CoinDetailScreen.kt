package com.works.coinall.screens

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.works.coinall.R
import com.works.coinall.components.AppHeader
import com.works.coinall.components.Converter
import com.works.coinall.extensions.formatPrice
import com.works.coinall.view_model.CoinViewModel
import com.works.coinall.view_model.FavoritesViewModel

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun CoinDetailScreen(
    coinId: String,
    modifier: Modifier = Modifier,
    viewModel: CoinViewModel = viewModel(),
    viewModel2: FavoritesViewModel = viewModel(),
    onBack: () -> Unit
) {
    BackHandler {
        onBack()
    }
    val coins by viewModel.coins.collectAsState()
    val coin = coins.find { it.id == coinId }

    Column(
        modifier = Modifier
            .padding(bottom = 100.dp)
            .background(Color.Black)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppHeader(
            title = "CoinAll.",
            subtitle = "Details",
        )
        Box(
            modifier = Modifier
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(colorResource(R.color.gluon_gray))
                .wrapContentSize()
        ) {
            Column(
                modifier = modifier
                    .padding(top = 20.dp, start = 15.dp, end = 15.dp, bottom = 15.dp),
            ) {
                coin?.let {
                    Row {
                        AsyncImage(
                            modifier = Modifier.size(100.dp),
                            model = it.image,
                            contentDescription = it.name
                        )
                        Column(
                            modifier = Modifier.padding(start = 15.dp)
                        ) {
                            Text(
                                text = it.name,
                                modifier = Modifier.padding(end = 30.dp),
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold
                            )
                            Text(
                                text = "(${it.symbol.uppercase()})",
                                modifier = Modifier.padding(end = 30.dp),
                                color = colorResource(R.color.santas_gray),
                                fontSize = 20.sp,
                            )
                            Spacer(modifier = modifier.padding(top = 10.dp))
                            Row {
                                Text(
                                    text = "$${it.currentPrice.formatPrice()}",
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = " (${it.priceChangePercentage24h.formatPrice()}%)",
                                    color = if (coin.priceChangePercentage24h >= 0) {
                                        colorResource(R.color.vegan_mastermind)
                                    } else {
                                        colorResource(R.color.glowing_brake_disc)
                                    },
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = modifier.padding(2.dp))
                            Box(
                                modifier = Modifier
                                    .background(
                                        colorResource(R.color.egg_toast),
                                        shape = RoundedCornerShape(7.dp)
                                    )
                                    .padding(horizontal = 4.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = "#${it.marketCapRank}",
                                    color = Color.Black,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    Spacer(modifier = modifier.padding(top = 20.dp))
                    Column {
                        Row {
                            Text(
                                text = "24H High ▲",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = "$${it.high24h.formatPrice()}",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                        Row {
                            Text(
                                text = "24H Low ▼",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = "$${it.low24h.formatPrice()}",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                        Row {
                            Text(
                                text = "Price Change 24h",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = "$${it.priceChange24h.formatPrice()}",
                                color = if (coin.priceChange24h >= 0) {
                                    colorResource(R.color.vegan_mastermind)
                                } else {
                                    colorResource(R.color.glowing_brake_disc)
                                },
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                        Row {
                            Text(
                                text = "Price Change 24h %",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = "${it.priceChangePercentage24h.formatPrice()}%",
                                color = if (coin.priceChangePercentage24h >= 0) {
                                    colorResource(R.color.vegan_mastermind)
                                } else {
                                    colorResource(R.color.glowing_brake_disc)
                                },
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text(
                                text = "Circulating Supply",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = it.circulatingSupply.formatPrice(),
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text(
                                text = "Total Supply",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = it.totalSupply.formatPrice(),
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text(
                                text = "Max Supply",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = if (it.maxSupply == 0.0) {
                                    "∞"
                                } else it.maxSupply.formatPrice(),
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text(
                                text = "All Time High ▲",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = "$${it.ath.formatPrice()}",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text(
                                text = "ATH Change %",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = "${it.athChangePercentage.formatPrice()}%",
                                color = if (coin.athChangePercentage >= 0) {
                                    colorResource(R.color.vegan_mastermind)
                                } else {
                                    colorResource(R.color.glowing_brake_disc)
                                },
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text(
                                text = "All Time Low ▼",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = "$${it.atl.formatPrice()}",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                        Row {
                            Text(
                                text = "ATL Change %",
                                color = Color.White,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.weight(1f))
                            Text(
                                text = "${it.atlChangePercentage.formatPrice()}%",
                                color = if (coin.atlChangePercentage >= 0) {
                                    colorResource(R.color.vegan_mastermind)
                                } else {
                                    colorResource(R.color.glowing_brake_disc)
                                },
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }

            coin?.let {
                IconButton(
                    onClick = { viewModel2.toggleFavorite(coin) },
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.TopEnd)
                        .padding(top = 20.dp, end = 15.dp)
                ) {
                    val isFav = viewModel2.favorites.any { it.id == coin.id }
                    Icon(
                        painter = painterResource(R.drawable.favorites),
                        contentDescription = "Favorite",
                        tint = if (isFav) {
                            colorResource(R.color.egg_toast)
                        } else {
                            colorResource(R.color.santas_gray)
                        },
                    )
                }
            }

            Image(
                painter = painterResource(R.drawable.coincall_icon_cleanbg),
                contentDescription = "Back",
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(200.dp)
                    .align(Alignment.Center)
                    .graphicsLayer(alpha = 0.07f)
            )
        }
        Converter(viewModel = viewModel, coinId = coinId)
    }
}


