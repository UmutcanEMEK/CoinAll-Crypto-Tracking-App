package com.works.coinall.components

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class CustomConcaveShape(
    private val depthFraction: Float = 0.55f,   // çukurun derinliği (0..1)
    private val leftFraction: Float = 0.35f,    // çukurun sol başlangıcı (0..1)
    private val rightFraction: Float = 0.65f,   // çukurun sağ bitişi (0..1)
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            moveTo(0f, 0f)
            lineTo(size.width * leftFraction, 0f)

            // Quadratic bezier ile içe çökme (çukur)
            quadraticBezierTo(
                size.width * 0.5f,
                size.height * depthFraction,
                size.width * rightFraction,
                0f
            )

            lineTo(size.width, 0f)
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }
        return Outline.Generic(path)
    }
}

