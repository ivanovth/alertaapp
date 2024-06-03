package com.example.alertaurbana.Screen.UtilCompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardBotonIcono(
    descripcion:String,
    icona: ImageVector,
    onClickBoton: () -> Unit
){


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .height(80.dp)
            .clickable {
                onClickBoton()
            },
       // backgroundColor = colorResource(id = R.color.BasedePagina)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center // Centra el contenido verticalmente,

        ) {
            Icon(
                imageVector = icona, modifier = Modifier.size(60.dp),
                contentDescription = descripcion,
                tint = Color.White
            )

        }
    }

}