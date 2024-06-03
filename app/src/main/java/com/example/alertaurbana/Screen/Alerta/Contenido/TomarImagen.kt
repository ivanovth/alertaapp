package com.example.alertaurbana.Screen.Alerta.Contenido

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Alerta.convertStringToBitmap

@Composable
fun TomarImagen(anchoColumna:Dp,anchoImagen:Dp ,alturaImagen:Dp,texto:String , onFoto: () -> Unit,imagen:String,requerido:Boolean){
    var miPainter3 by remember { mutableStateOf<Painter?>(null) }
    Column(
        modifier = Modifier
            .width(anchoColumna)
            .padding(vertical = 3.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (imagen != "") {
            miPainter3 =
                rememberAsyncImagePainter(convertStringToBitmap(imagen))
        } else {
            miPainter3 = painterResource(id = R.drawable.image22)
        }
        Image(
            painter = miPainter3
                ?: painterResource(id = R.drawable.image22),
            contentDescription = null,
            modifier = Modifier
                .height(alturaImagen).width(anchoImagen)

                .background(MaterialTheme.colorScheme.background)
                .clickable {
                    // Handle image option click
                    onFoto()
                },
            contentScale = ContentScale.Fit,

            )

        Row (horizontalArrangement = Arrangement.Start){
            Text(texto)
            if(requerido)
            {Text(" *",color= Color.Red, fontSize = 20.sp)}

        }

//        IconButton(
//            onClick = {
//                onFoto()
////                navController.navigate(Screen.CAMARA.route + "/" + 3)
////                viewModel.imagenVer3 = false
//                // Handle camera button click
//            }
//        ) {
//            Icon(
//                imageVector = Icons.Default.CameraAlt,
//                contentDescription = "Camera"
//            )
//        }
    }
}