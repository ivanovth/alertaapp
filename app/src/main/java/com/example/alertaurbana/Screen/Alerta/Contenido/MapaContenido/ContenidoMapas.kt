package com.example.alertaurbana.Screen.Alerta.Contenido.MapaContenido

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertasItem
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Alerta.Contenido.ViewModels.MapaScreenViewModel

@Composable
fun Leyendas(texto:String,color: Color){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .width(40.dp)
                .height(10.dp)
                .background(color)
        ) {
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = texto,
            fontWeight = FontWeight.Black,
            color = color
        )
    }
}


@Composable
fun MostrarFotos(elementos: List<DtoAlertasItem>,updateLatitud: (latitud: Double) -> Unit,updateLongitud: (latitud: Double) -> Unit){
    val items= elementos.filter {   it.Fotos!=null}
    var currentIndex by remember { mutableStateOf(0) }
  //  var LocationText by rememberSaveable { mutableStateOf("") }
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .width(352.dp)
            .height(200.dp)
            .background(Color.Transparent))
    {
        Card(
            modifier = Modifier
                .padding(0.dp) // Añade un padding alrededor del card para evitar que el contenido toque los bordes de la pantalla.
                // Aquí defines el borde del card, en este caso un borde negro de 2dp.
                .fillMaxSize(),
                    shape = RoundedCornerShape(16.dp),
                 // Redondear las esquinas del Card.
           // elevation = 10.dp //

            // La elevación del card para dar un efecto de sombra.
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {


                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .fillMaxHeight()
                        .background(Color.Gray).clickable(enabled = currentIndex > 0) { if (currentIndex > 0) currentIndex--
                            updateLatitud(items[currentIndex].Fotos[0].latitud)
                            updateLongitud(items[currentIndex].Fotos[0].longitud)
//                            viewmodel.updateLatitud(items[currentIndex].Fotos[0].latitud)
//                            viewmodel.updateLongitud(items[currentIndex].Fotos[0].longitud)
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Favorite Icon",
                        modifier = Modifier.fillMaxSize()
                    )

                }
                Box(
                    modifier = Modifier
                        .width(250.dp)
                        .fillMaxHeight()
                        .background(Color.White)
                ) {
                    Row(modifier= Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically ) {
                        Box(modifier= Modifier
                            .width(120.dp)
                            .fillMaxHeight()){
                            Column(modifier= Modifier.padding(8.dp)) {
                                Text(text = items[currentIndex].TipoDeAlerta.Nombre.toString(), fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
                                Text(text = "Descripción:", fontWeight = FontWeight.Bold, fontSize = 10.sp)
                                Text(text = items[currentIndex].Descripcion.toString(), fontWeight = FontWeight.Light, fontSize = 10.sp)
                                Text(text = "Fecha:", fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
                                Text(text = items[currentIndex].FechaAlerta.toString(), fontWeight = FontWeight.Light, fontSize = 10.sp)
                                Text(text = "Hora:", fontWeight = FontWeight.ExtraBold, fontSize = 10.sp)
                                Text(text = items[currentIndex].HoraAlerta.toString(), fontWeight = FontWeight.Light, fontSize = 10.sp)
//Text(text = "Hora de la lerta")

                            }

                        }


//                        Image(
//                            painter = painterResource(id = R.drawable.urbi_alerta_2), // Reemplaza 'my_image' con el nombre de tu recurso
//                            contentDescription = "Description accessible de la image",
//                            modifier= Modifier
//                                .width(100.dp)
//                                .height(300.dp)
//                        )
                        Image(
                            painter = // Define your placeholder in drawable
                            rememberAsyncImagePainter(ImageRequest.Builder // Define your error placeholder in drawable
                                (LocalContext.current)
                                .data(data = "https://alerta.municusco.com/media/${items[currentIndex].Fotos[0].Foto}")
                                .apply(block = fun ImageRequest.Builder.() {
                                    crossfade(true)
                                    placeholder(R.drawable.load) // Define your placeholder in drawable
                                    error(R.drawable.error) // Define your error placeholder in drawable
                                }).build()
                            ),
                            contentDescription = "Imagen Cargada",
                            modifier = Modifier .width(130.dp).height(200.dp).padding(top=10.dp, bottom = 10.dp ),
                            contentScale = ContentScale.Fit
                        )


                    }


                }
                Box(
                    modifier = Modifier
                        .width(50.dp)
                        .fillMaxHeight()
                        .background(Color.Gray)


                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowForwardIos,
                        contentDescription = "Favorite Icon",
                        modifier = Modifier.fillMaxSize().clickable(enabled = currentIndex < items.size - 1) {
                            if (currentIndex < items.size - 1) currentIndex++
                            updateLatitud(items[currentIndex].Fotos[0].latitud)
                            updateLongitud(items[currentIndex].Fotos[0].longitud)
//                            viewmodel.updateLatitud(items[currentIndex].Fotos[0].latitud)
//                            viewmodel.updateLongitud(items[currentIndex].Fotos[0].longitud)
                        }
                    )


                }


            }
        }


    }

}

@Composable
fun ShowImageDialog(isDialogOpen: MutableState<Boolean>, imagePainter: Painter) {
    if (isDialogOpen.value) {
        Dialog(onDismissRequest = { isDialogOpen.value = false }) {
            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                // Contenido del diálogo
                Image(
                    painter = imagePainter,
                    contentDescription = "Descripción de la imagen",
                    modifier = Modifier.fillMaxSize()
                )
                // Botón para cerrar el diálogo
                Button(
                    onClick = { isDialogOpen.value = false },
                    modifier = Modifier.align(Alignment.TopEnd).padding(top = 20.dp, end = 20.dp)
                ) {
                    Text("Cerrar")
                }
            }
        }
    }
}
