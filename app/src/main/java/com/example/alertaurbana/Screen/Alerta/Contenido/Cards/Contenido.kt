package com.example.alertaurbana.Screen.Alerta.Contenido.Cards

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Alerta.convertStringToBitmap

@Composable
fun TakeClosePhotoCard(titulo:String, contenido:String, onFoto: () -> Unit, imagen:String, requerido:Boolean) {
    var miPainter3 by remember { mutableStateOf<Painter?>(null) }


    Card(
        modifier = Modifier.clickable { onFoto(

        ) }
            .padding(4.dp)
            .fillMaxWidth()
            .border(1.5.dp, Color.Gray, RoundedCornerShape(8.dp))
            .height(86.dp),
        shape = RoundedCornerShape(8.dp),
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

//            Box(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .width(90.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.image22),
//                    contentScale = ContentScale.Fit,
//                    contentDescription = null,
//                    modifier = Modifier.size(70.dp)
//                )
//            }


            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(90.dp),
                contentAlignment = Alignment.Center
            ){
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.size(70.dp)

                ) {
                    if (imagen != "") {
                        miPainter3 =
                            rememberAsyncImagePainter(convertStringToBitmap(imagen))
                        Image(
                            painter = miPainter3
                                ?: painterResource(id = R.drawable.image22),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                          //  modifier = Modifier.padding(2.dp)

                        )
                    } else {
                        miPainter3 = painterResource(id = R.drawable.image22)
                        Image(
                            painter = miPainter3
                                ?: painterResource(id = R.drawable.image22),
                            contentScale = ContentScale.Fit,
                            contentDescription = null,
                            modifier = Modifier.padding(2.dp)

                        )
                    }

                }
            }



            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .border(0.dp, Color.Transparent, RoundedCornerShape(8.dp))
                    .height(80.dp),
                shape = RoundedCornerShape(6.dp),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    //  verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(6.dp)
                ) {

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row{
                        Text(
                            text = titulo,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface,


                            )
                            if(requerido){


                        Text(
                            text = " *",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.error,

                            )}}

                    }

                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = contenido,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }


                }
            }


        }
    }
}

//@Preview
//@Composable
//fun llamar() {
//    TakeClosePhotoCard({})
//}