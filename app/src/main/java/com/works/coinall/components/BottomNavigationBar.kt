package com.works.coinall.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.works.coinall.R

@Composable
fun BottomNavigationBar(
    items: List<BottomBarItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
) {
    Column {
        /*Divider(
            color = Color.Gray,
            thickness = 0.5.dp  // bottombarı ayıran çizgi
        )*/
        val selectedColor = colorResource(id = R.color.egg_toast)
        NavigationBar(
            containerColor = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = Color.Black,
                        selectedIconColor = selectedColor,
                        selectedTextColor = selectedColor
                    ),
                    selected = index == selectedIndex,
                    onClick = { onItemSelected(index) },
                    icon = {
                        Icon(
                            modifier = Modifier.size(22.dp),
                            painter = if (index == selectedIndex) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title,
                            //tint = if (index == selectedIndex) item.selectedColor else item.unselectedColor,
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            color = if (index != selectedIndex) {
                                colorResource(R.color.white)
                            } else {
                                colorResource(R.color.egg_toast)
                            }
                        )
                    }
                )
            }
        }
    }
}

