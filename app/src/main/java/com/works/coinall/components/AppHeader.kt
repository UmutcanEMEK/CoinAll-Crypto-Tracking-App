package com.works.coinall.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.works.coinall.R

@Composable
fun AppHeader(
    title: String,
    subtitle: String,

    ) {
    Column {
        Text(
            text = title,
            modifier = androidx.compose.ui.Modifier.padding(top = 46.dp),
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = subtitle,
            modifier = androidx.compose.ui.Modifier.align(alignment = Alignment.End),
            color = colorResource(R.color.egg_toast),
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}