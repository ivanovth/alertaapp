package com.example.alertaurbana.Screen.Alerta.Contenido


import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton


import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.alertaurbana.Navigation.Screen
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Alerta.AlertaViewmodel
import com.example.alertaurbana.Screen.Alerta.Contenido.Cards.TakeClosePhotoCard
import com.example.alertaurbana.Screen.Alerta.Contenido.ViewModels.NotificacionesViewmodel

import com.example.alertaurbana.Screen.Login.MyTopAppBarPop
import com.example.alertaurbana.Screen.Textos.ConstantsAlerta
import com.example.alertaurbana.Screen.UtilCompose.AccesoPref
import com.mapbox.maps.MapboxExperimental


@OptIn(MapboxExperimental::class)
@Composable
fun NotificaionesScreeen(
    navController: NavHostController,
    viewModel: AlertaViewmodel = hiltViewModel()
) {

    Scaffold(
        topBar = { MyTopAppBarPop(titulo = ConstantsAlerta.NOTIFICACIONTITULO) { navController.popBackStack() } },
        bottomBar = { },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                val context= LocalContext.current
                // MapboxComposable33()
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    TakeClosePhotoCard("Foto cercana","Foto cercana  para ubicar las coordenadas de la alerta.",{navController.navigate(Screen.CAMARA.route + "/" + 1)
                        viewModel.imagenVer1 = false}, AccesoPref().obtenerimagen1() ?: "",true)
                    NotificationCard()

                    CardBorder()
                    CardBorder()

                }


            }
        },

        )


}


@Composable
fun NotificationCard() {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(6.dp),
        //elevation = 4.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.urbi_alerta), // Reemplazar 'notification_image' por tu imagen
                contentDescription = "Notification Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Notificación ",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Aquí va el detalle de la notificación, proporcionando más información relevante al usuario.",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}


@Preview
@Composable
fun CardBorder() {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color =  CardDefaults.cardColors().containerColor,
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .height(210.dp)
            .padding(10.dp),
        shadowElevation = 10.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {


                Text(
                    text = "Alerta",
                    fontSize = 18.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(2.dp))
                Box(modifier = Modifier.padding(end = 8.dp)) {
                    Column {
                        Text(fontSize = 14.sp,text = "Detalle:", fontWeight = FontWeight.SemiBold)
                        Text(
                          lineHeight = 15.sp,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Justify,
                            text = "It is a long established fact that a reader will be distracted by the readable content ...."
                        )
                    }

                }

                Spacer(modifier = Modifier.height(2.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Hora: 12:45pm",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Fecha: 12/12/2066",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.titleLarge
                    )
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
//                        tint = Color(0xFFF6B266),
//                        contentDescription = null
//                    )
//
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
//                        tint = Color(0xFFF6B266),
//                        contentDescription = null
//                    )
//
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
//                        tint = Color(0xFFF6B266),
//                        contentDescription = null
//                    )
//                    Icon(
//                        painter = painterResource(id = R.drawable.baseline_star_outline_24),
//                        tint = Color(0xFFF6B266),
//                        contentDescription = null
//                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                OutlinedButton(
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Black,
                        containerColor = Color.White
                    ),
                    modifier = Modifier.height(25.dp),
                    contentPadding = PaddingValues(3.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "......",
                        fontSize = 9.sp,
                        fontWeight = FontWeight.ExtraBold,
                        style = LocalTextStyle.current
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.size(width = 100.dp, height = 140.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.urbi_alerta_2),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
        }
    }
}

//fun Style.Builder.addCustomLayersAndSources() {
//    +vectorSource("my-source") {
//        url("https://example.com/tiles/{z}/{x}/{y}")
//        minzoom(6)
//        maxzoom(14)
//    }
//
//    +fillLayer("my-layer", "my-source") {
//        sourceLayer("my-source-layer")
//        fillColor("rgba(255, 0, 0, 0.5)")
//    }
//}

//fun style.Builder.addCustomLayersAndSources() {
//    with(vectorSource("my-source") {
//        tileSet("https://example.com/tiles/{z}/{x}/{y}")
//        minzoom(6)
//        maxzoom(14)
//    }) {
//        this@addCustomLayersAndSources.addSource(this)
//    }
//
//    with(fillLayer("my-layer", "my-source") {
//        sourceLayer("my-source-layer")
//        fillColor("rgba(255, 0, 0, 0.5)")
//    }) {
//        this@addCustomLayersAndSources.addLayer(this)
//    }
//}