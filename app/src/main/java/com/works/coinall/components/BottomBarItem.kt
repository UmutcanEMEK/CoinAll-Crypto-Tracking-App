package com.works.coinall.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.works.coinall.R


data class BottomBarItem(
    val title: String,
    //val icon: Int,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val unselecedColor: Int,
)

@Composable
fun bottomBarItems(): List<BottomBarItem> {
    return listOf(
        BottomBarItem(
            title = "Live Data",
            selectedIcon = painterResource(R.drawable.live_data_selected),
            unselectedIcon = painterResource(R.drawable.live_data),
            unselecedColor = R.color.santas_gray
        ),
        BottomBarItem(
            title = "Favorites",
            selectedIcon = painterResource(R.drawable.favorites_selected),
            unselectedIcon = painterResource(R.drawable.favorites),
            unselecedColor = R.color.santas_gray
        ),
        /*BottomBarItem(
            title = "Converter",
            selectedIcon = painterResource(R.drawable.converter_selected),
            unselectedIcon = painterResource(R.drawable.converter),
            unselecedColor = R.color.santas_gray
        )*/
    )
}