package com.works.coinall.extensions

import java.text.DecimalFormat

fun Double.formatPrice(): String {
    val price = this
    val formatter = DecimalFormat("#,###.00")
    val formatterBtc = DecimalFormat("#,###")
    return when (price) {
        in 0.0000001..0.000001 -> String.format("%.9f", price)
        in 0.000001..0.00001 -> String.format("%.8f", price)
        in 0.00001..0.0001 -> String.format("%.7f", price)
        in 0.0001..0.001 -> String.format("%.6f", price)
        in 0.001..0.01 -> String.format("%.5f", price)
        in 0.01..0.1 -> String.format("%.4f", price)
        in 0.1..0.99 -> String.format("%.3f", price)
        in 0.99..10000.0 -> formatter.format(price)

        in -0.000001..-0.0000001 -> String.format("%.9f", price)
        in -0.00001..-0.000001 -> String.format("%.8f", price)
        in -0.0001..-0.00001 -> String.format("%.7f", price)
        in -0.001..-0.0001 -> String.format("%.6f", price)
        in -0.01..-0.001 -> String.format("%.5f", price)
        in -0.1..-0.01 -> String.format("%.4f", price)
        in -0.99..-0.1 -> String.format("%.3f", price)
        in -10000.0..-0.99 -> formatter.format(price)

        else -> formatterBtc.format(price)
    }
}