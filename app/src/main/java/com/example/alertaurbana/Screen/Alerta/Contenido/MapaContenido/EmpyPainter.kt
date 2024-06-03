package com.example.alertaurbana.Screen.Alerta.Contenido.MapaContenido

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter

class EmpyPainter:Painter() {
    override val intrinsicSize: Size
        get() = Size.Unspecified

    override fun DrawScope.onDraw() {
        // No dibuja nada
    }
}