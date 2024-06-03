package com.example.alertaurbana.Screen.mapas

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.alertaurbana.Navigation.Screen
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Alerta.AlertaViewmodel

import com.example.alertaurbana.Screen.Alerta.Contenido.enableLocationComponent

import com.example.alertaurbana.Screen.Login.MyBottomAppBar
import com.example.alertaurbana.Screen.Login.MyTopAppBar
import com.example.alertaurbana.Screen.Textos.ConstantsAlerta
import com.example.alertaurbana.Screen.UtilCompose.AccesoPref
import com.mapbox.geojson.Point

import com.mapbox.maps.CameraOptions
import com.mapbox.maps.Style
import com.mapbox.maps.extension.style.StyleExtensionImpl
import com.mapbox.maps.extension.style.expressions.dsl.generated.match
import com.mapbox.maps.extension.style.image.image
import com.mapbox.maps.extension.style.layers.generated.fillLayer
import com.mapbox.maps.extension.style.layers.generated.rasterLayer
import com.mapbox.maps.extension.style.layers.generated.symbolLayer
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor
import com.mapbox.maps.extension.style.sources.generated.rasterSource
import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style


@Composable
fun MapaSeleccionCoordenada(
    navController: NavHostController,
    viewModel: AlertaViewmodel = hiltViewModel()
) {


    Scaffold(
        topBar = { MyTopAppBar(ConstantsAlerta.TITULOALERTA) },
        bottomBar = { MyBottomAppBar() },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(bottom = 20.dp)
            ) {


                MapboxComposable33(LocalContext.current) { latitud, longitud ->
                    viewModel.Asignar_CoordenadaSeleccionada(
                        latitud,
                        longitud
                    )
                }
                Image(

                    painter = painterResource(id = R.drawable.ubicacion),
                    contentDescription = null,
                    modifier = Modifier
                        .height(90.dp)
                        .width(70.dp)

                        .background(Color.Transparent)
                        .align(Alignment.Center)
                        .padding(bottom = 35.dp),


                    )
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(all = 8.dp)
                ) { Cardxd(navController, viewModel)  }


                // MapViewComposable(LocalContext.current)

            }
        }
    )
}


@Composable
fun Cardxd(navController: NavHostController, viewModel: AlertaViewmodel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp) // Set height of the card
            .background(
                color = Color.White.copy(alpha = 0.5f), // Semi-transparent white background
                shape = RoundedCornerShape(12.dp) // Rounded corners
            )
        //elevation = 6.dp // Shadow for depth
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Selecciona La Ubicación en el mapa",
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp)) // Space between text and button

            Button(
                onClick = {

                    viewModel.AsignarCoordenadas()
                    navController.popBackStack()

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent // Transparent button background
                ),
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(24.dp) // Rounded button
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF2196F3), // Light blue
                                    Color(0xFF3F51B5) // Deep blue
                                )
                            )
                        )
                        .fillMaxSize()
                ) {
                    Text(
                        text = "Aceptar",
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center),
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}


//@Composable
//fun MapViewComposable(context: Context) {
//    val mapView = remember {
//        MapView(context).apply {
//
//            onCreate(Bundle()) // Importante para inicializar el mapa correctamente
//
//        }
//
//
//    }
//
//    AndroidView(
//        factory = { mapView }, update = {mapView->
//            mapView.getMapAsync{map->
//                val styleJson2 = """
//            {
//              "version": 8,
//              "sources": {
//                "osm-tiles": {
//                  "type": "raster",
//                  "tiles": [
//                    "https://tile.openstreetmap.org/{z}/{x}/{y}.png"
//                  ],
//                  "tileSize": 256
//                }
//              },
//              "layers": [
//                {
//                  "id": "osm-layer",
//                  "type": "raster",
//                  "source": "osm-tiles"
//                }
//              ]
//            }
//            """.trimIndent()
//
//                val customStyleJson = """
//                {
//                  "version": 8,
//                  "sources": {
//                    "google-tiles": {
//                      "type": "raster",
//                      "tiles": [
//                        "https://mt1.google.com/vt/lyrs=r&x={x}&y={y}&z={z}"
//                      ],
//                      "tileSize": 256
//                    }
//                  },
//                  "layers": [
//                    {
//                      "id": "google-layer",
//                      "type": "raster",
//                      "source": "google-tiles"
//                    }
//                  ]
//                }
//            """.trimIndent()
//                //  map.setStyle("https://demotiles.maplibre.org/style.json")
//                map.setStyle(
//                    // Style.Builder().fromUri("http://www.google.cn/maps/vt?lyrs=s@189&gl=cn&x={x}&y={y}&z={z}")
//                    Style.Builder().fromJson(customStyleJson )
//
//                )
//                map.cameraPosition = CameraPosition.Builder().target(LatLng(-13.523138500000000,-71.974216999999996 )).zoom(16.0).build()
//
//            }
//
//
//        }
//    )
//
//    // Manejar el ciclo de vida de Compose
//    DisposableEffect(mapView) {
//        onDispose {
//            mapView.onDestroy()
//        }
//    }
//
//
//
//}


@Composable
fun MapboxComposable33(
    context: Context,
    agregarCoordenadas: (latitud: Double, longitud: Double) -> Unit
) {


    val mapView = remember {
        com.mapbox.maps.MapView(context).apply {
            val preferencias = AccesoPref()
            // viewModel.verGps(context)
            var latitud: String? = preferencias.obtenerlatitud()
            var longitud: String? = preferencias.obtenerlongitud()

            if (latitud == "") {
                latitud = "-13.523138500000000"
            }
            if (longitud == "") {
                longitud = "-71.974216999999996"
            }


            mapboxMap.apply {
                if (longitud != null) {
                    if (latitud != null) {
                        setCamera(
                            CameraOptions.Builder()
                                .zoom(12.0)
                                //-71.974216999999996, -13.523138500000000
                                .center(Point.fromLngLat(longitud.toDouble(), latitud.toDouble()))
                                .build()
                        )
                    }
                }

                loadStyle(
                    styleExtension = style(Style.MAPBOX_STREETS) {

                    }
                ) {
                    val image = context.resources.getDrawable(R.drawable.gps3, null)
                    image(
                        "idimage",
                        ContextCompat.getDrawable(context, R.drawable.gps1)!!.toBitmap()
                    )

                    val simbol = symbolLayer("layer", "icon") {
                        iconImage("idimage")
                        iconAnchor(IconAnchor.BOTTOM)
                        iconSize(
                            0.3
                        )

                    }


//                    gestures.addOnMapClickListener { point ->
//                        val anotations = annotations
//                        val circleAnnotationManager = anotations.createCircleAnnotationManager()
//                        circleAnnotationManager.deleteAll()
//                        val circleAnnotationOptions: CircleAnnotationOptions =
//                            CircleAnnotationOptions()
//                                .withPoint(Point.fromLngLat(point.longitude(), point.latitude()))
//                                .withCircleRadius(8.0)
//                                .withCircleColor("#ee4e8b")
//                                .withCircleStrokeWidth(2.0)
//                                .withCircleStrokeColor("#ffffff")
//                        circleAnnotationManager.create(circleAnnotationOptions)
////
//                        true
//                    }
                    addOnCameraChangeListener {
                        val centerPoint = cameraState.center
                        //viewModel.Asignar_CoordenadaSeleccionada(centerPoint.latitude(),centerPoint.longitude())
                        agregarCoordenadas(centerPoint.latitude(), centerPoint.longitude())


                        // Log.d("mapcenter", "Center coordinates: ${centerPoint.latitude()}, ${centerPoint.longitude()}")
                    }

                }


            }


        }
    }

    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize(),
        update = {
            // Configura aquí tu componente de ubicación si los permisos ya están concedidos
            // enableLocationComponent(mapView, context)
        }
    )

}


