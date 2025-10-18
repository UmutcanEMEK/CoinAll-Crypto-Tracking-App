package com.works.coinall.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.works.coinall.R
import com.works.coinall.extensions.formatPrice
import com.works.coinall.view_model.CoinViewModel

@Composable
fun Converter(
    viewModel: CoinViewModel = viewModel(),
    coinId: String
){
    val coins by viewModel.coins.collectAsState()
    val coin = coins.find { it.id == coinId }
    coin?.let {
        var text by remember { mutableStateOf("1") }
        Box(
            modifier = Modifier
                .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                .clip(shape = RoundedCornerShape(25.dp))
                .background(colorResource(R.color.gluon_gray))
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 10.dp,
                    bottom = 20.dp

                )
            ) {
                Text(
                    text = "${it.name} to USD converter",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Row {
                    TextField(
                        value = text,
                        onValueChange = { newText ->
                            if (newText.isEmpty() || newText.all { it.isDigit() || it == '.' } && newText.count { it == '.' } <= 1) {
                                text = newText
                            }
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Decimal
                        ),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = colorResource(R.color.egg_toast),
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = colorResource(R.color.egg_toast),
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                2.dp, colorResource(R.color.egg_toast),
                                RoundedCornerShape(10.dp)
                            )
                            .clip(shape = RoundedCornerShape(10.dp)),
                    )
                    Spacer(modifier = Modifier.padding(start = 10.dp))

                    val inputNumber =
                        text.toDoubleOrNull() ?: 0.0
                    val result = (it.currentPrice * inputNumber).formatPrice()

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(
                                colorResource(R.color.egg_toast),
                                RoundedCornerShape(10.dp)
                            )
                            .height(56.dp)
                    ) {
                        Text(
                            text = "$$result",
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 15.dp, end = 10.dp),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}