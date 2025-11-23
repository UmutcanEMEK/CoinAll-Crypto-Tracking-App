package com.works.coinall.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.works.coinall.R


@Composable
fun CoinListSkeletonItem(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Coin Image Placeholder
        Box(
            modifier = Modifier
                .size(39.dp)
                .placeholder(
                    visible = true,
                    highlight = PlaceholderHighlight.shimmer(),
                    shape = RoundedCornerShape(20.dp)
                )
        )

        // Name & Symbol Placeholder
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .fillMaxWidth(0.5f)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .height(17.dp)
                    .fillMaxWidth(0.3f)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }

        // Price & Change Placeholder
        Column(
            modifier = Modifier.padding(end = 10.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(18.dp)
                    .width(80.dp)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Box(
                modifier = Modifier
                    .align(Alignment.End)
                    .height(17.dp)
                    .width(50.dp)
                    .placeholder(
                        visible = true,
                        highlight = PlaceholderHighlight.shimmer(),
                        shape = RoundedCornerShape(4.dp)
                    )
            )
        }

        // Favorite Icon Placeholder
        Icon(
            painter = painterResource(id = R.drawable.favorites),
            contentDescription = "Favorite Skeleton",
            tint = Color.White,
            modifier = Modifier.size(40.dp).padding(top = 0.dp, end = 10.dp)
        )
    }
}

@Composable
fun CoinListSkeleton(count: Int = 12) {
    Column(modifier = Modifier.fillMaxWidth()) {
        repeat(count) {
            CoinListSkeletonItem()
        }
    }
}
