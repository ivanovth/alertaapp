package com.example.alertaurbana.Screen.Alerta.Contenido

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlaylistAddCircle
import androidx.compose.material.icons.filled.Settings

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination

import androidx.navigation.NavHostController
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertasItem
import com.example.alertaurbana.Navigation.Screen
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Alerta.Contenido.MapaContenido.Leyendas
import com.example.alertaurbana.Screen.Alerta.Contenido.MapaContenido.MostrarFotos
import com.example.alertaurbana.Screen.Alerta.Contenido.ViewModels.MapaScreenViewModel

import com.example.alertaurbana.Screen.Login.MyBottomAppBar
import com.example.alertaurbana.Screen.Login.MyTopAppBar
import com.example.alertaurbana.Screen.Login.MyTopAppBarPop
import com.example.alertaurbana.Screen.Textos.ConstantsAlerta
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection

import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.ImageHolder
import com.mapbox.maps.MapView
import com.mapbox.maps.Style

import com.mapbox.maps.extension.style.StyleExtensionImpl

import com.mapbox.maps.extension.style.expressions.dsl.generated.match
import com.mapbox.maps.extension.style.expressions.generated.Expression.Companion.interpolate
import com.mapbox.maps.extension.style.image.image

import com.mapbox.maps.extension.style.layers.generated.fillLayer
import com.mapbox.maps.extension.style.layers.generated.symbolLayer
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor
import com.mapbox.maps.extension.style.sources.generated.geoJsonSource

import com.mapbox.maps.extension.style.sources.generated.vectorSource
import com.mapbox.maps.extension.style.style
import com.mapbox.maps.plugin.LocationPuck2D
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.locationcomponent.location


@Composable
fun MapaScreen(navController: NavHostController, viewModel: MapaScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    var showDialog by remember { mutableStateOf(false) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    val icons = listOf(Icons.Default.Map, Icons.Default.Home, Icons.Default.Settings)
    Scaffold(
        topBar = {
            MyTopAppBarPop(titulo = ConstantsAlerta.RIESGOSYALERTASSCREEN) {
                navController.popBackStack()
            }
        },
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()

                    .padding(innerPadding)
                //   .padding(bottom = 20.dp)
            ) {
//                if(viewModel.estallenadoTipodemapa==1)
//                {
                MapboxComposable33(viewModel, 1, context)
//                }
//                  else{
//                    MapboxComposable33(viewModel,2)
//                  }
                if (viewModel.estadoNumero.value == 1) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .height(150.dp)
                                .width(300.dp)
                                .padding(start = 5.dp, bottom = 30.dp)
                                .background(Color.Transparent)
                        ) {
                            Column() {

                                Leyendas(
                                    texto = "Zonas de Muy Alto Peligro",
                                    color = Color(255, 58, 23)
                                )
                                Leyendas(
                                    texto = "Zonas de Alto Peligro",
                                    color = Color(255, 204, 20)
                                )
                                Leyendas(
                                    texto = "Zonas de Medio Peligro",
                                    color = Color(255, 247, 52)
                                )
                                Leyendas(
                                    texto = "Zonas de Bajo Peligro",
                                    color = Color(188, 255, 5)
                                )


                            }

                        }

                    }
                }


                if (viewModel.estadoNumero.value == 3) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 5.dp)


                    ) {
                        if (viewModel.respuestaObtenerAlertas == "ok")
                        //{llenarPuntos(context,viewModel.ListarAlertasList)}
                        //MostrarFotos(viewModel.ListarAlertasList,{latitud -> viewModel.updateLatitud(latitud)},{ longitud-> viewModel.updateLongitud(longitud)})

                            SliderBanner2(viewModel.ListarAlertasList,
                                { index -> viewModel.llenarSheet(index) },
                                { latitud -> viewModel.updateLatitud(latitud) },
                                { longitud -> viewModel.updateLongitud(longitud) })
                    }
                }





                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 50.dp)
                ) {

                    FloatingActionButton(
                        onClick = { isMenuExpanded = true },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .height(40.dp)
                            .width(40.dp),
                        containerColor = MaterialTheme.colorScheme.onPrimary
                    ) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }


                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Seleccione Una Opción:",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Left
                        )
                        HorizontalDivider(
                            color = Color.Gray, // Color de la línea
                            thickness = 1.dp, // Grosor de la línea
                            modifier = Modifier.padding(vertical = 8.dp)
                        )

                        DropdownMenuItem(
                            text = { Text("Ver Zonas de Riesgos ") },
                            onClick = {
                                viewModel.updateEstado(1)
                                isMenuExpanded = false
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Map,
                                    contentDescription = "Leading Icon",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            },
                            modifier = Modifier.height(30.dp)
                        )
                        HorizontalDivider(
                            color = Color.Gray, // Color de la línea
                            thickness = 1.dp, // Grosor de la línea
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        DropdownMenuItem(
                            text = { Text("Ver Zonificaciones") },
                            onClick = {
                                viewModel.updateEstado(2)
                                isMenuExpanded = false
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Map,
                                    contentDescription = "Leading Icon",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            },
                            modifier = Modifier.height(30.dp)
                        )
                        HorizontalDivider(
                            color = Color.Gray, // Color de la línea
                            thickness = 1.dp, // Grosor de la línea
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        DropdownMenuItem(text = { Text("Ver las Ultimas Alertas") }, onClick = {
                            viewModel.updateEstado(3)

                            isMenuExpanded = false
                        },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.PlaylistAddCircle,
                                    contentDescription = "Leading Icon",
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                            },
                            modifier = Modifier.height(30.dp)
                        )

                        HorizontalDivider(
                            color = Color.Gray, // Color de la línea
                            thickness = 1.dp, // Grosor de la línea
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
//                        DropdownMenuItem(text = { Text("Agregar Una Alerta") }, onClick = {
//                            viewModel.updateEstado(1)
//                            navController.navigate(Screen.AGREGARALERTAS.route){
//                                popUpTo(Screen.InicioScreen.route) {
//                                    inclusive = true  // Incluye la ruta en el pop, cerrando también esa pantalla
//                                }
//                                launchSingleTop = true  //
//                            }
//                            isMenuExpanded = false },
//                            leadingIcon = {
//                                Icon(
//                                    imageVector = Icons.Default.AddAlert,
//                                    contentDescription = "Leading Icon",
//                                    modifier = Modifier.padding(end = 8.dp)
//                                )
//                            },
//                            modifier = Modifier.height(30.dp)
//                        )


                    }

                }


            }
        },
//        floatingActionButton = {
//            FloatingActionButton(onClick = {
//                if (viewModel.estallenadoTipodemapa == 1) {
//                    viewModel.estallenadoTipodemapa = 2
//                    viewModel.updateEstado(2)
//                } else {
//                    viewModel.estallenadoTipodemapa = 1
//                    viewModel.updateEstado(1)
//                }
//                Log.d("cambiando", viewModel.estallenadoTipodemapa.toString())
//            }) {
//                Icon(Icons.Default.Add, contentDescription = "Add")
//            }
//        }
    )


}


const val SOURCE_ID = "mapillary"
const val LAYER_ID = SOURCE_ID
const val SOURCE_LAYER_ID = "pdu2013_diagnostico.tg_pdu2013_diag_ambiental_peligro_remocion_masa"
const val BELOW_LAYER_ID = "road-label-simple"

@Composable
fun MapboxComposable33(viewModel: MapaScreenViewModel, nro: Int, context: Context) {

    viewModel.ObtenerAlertas()
    val selectedItem by remember { mutableIntStateOf(viewModel.estallenadoTipodemapa) }
    val estaso by viewModel.estadoNumero.observeAsState()
    Log.d("llegara aca??", estaso.toString())

    val mapView = remember {
        MapView(context).apply {

            mapboxMap.apply {
                setCamera(
                    CameraOptions.Builder()
                        .zoom(12.0)
                        .center(Point.fromLngLat(-71.974216999999996, -13.523138500000000))
                        .build()
                )
                loadStyle(

                    styleExtension = style(Style.SATELLITE_STREETS) {
                        if (viewModel.respuestaObtenerAlertas == "ok") {
                            llenarPuntos(context, viewModel.ListarAlertasList)
                        }


                    }
                ) {


                }

            }


        }
    }


    LaunchedEffect(estaso) {
        if (estaso == 1) {
            mapView.mapboxMap.apply {

                loadStyle(
                    styleExtension = style(Style.SATELLITE_STREETS) {
                        zonas()
                    }
                )

            }
        } else if (estaso == 2) {
            mapView.mapboxMap.apply {

                loadStyle(
                    styleExtension = style(Style.SATELLITE_STREETS) {
                        zonas2()
                    }
                )
            }
        } else if (estaso == 3) {

            mapView.mapboxMap.apply {

                loadStyle(
                    styleExtension = style(Style.SATELLITE_STREETS) {
                        if (viewModel.respuestaObtenerAlertas == "ok") {
                            Log.d("dfdfsd", viewModel.ListarAlertasList.toString())
                            llenarPuntos(context, viewModel.ListarAlertasList)

                        }

                    }
                )
            }


        }

    }
    LaunchedEffect(viewModel.latitud2) {
        if (viewModel.respuestaObtenerAlertas == "ok") {
            Log.d("dfdfsd", viewModel.ListarAlertasList.toString())
            val lista2 = viewModel.ListarAlertasList.filter { it.Fotos != null }
            if (lista2.isNotEmpty()) {
                mapView.mapboxMap.flyTo(
                    CameraOptions.Builder()
                        .center(Point.fromLngLat(viewModel.longitud3, viewModel.latitud2))
                        .zoom(18.0)
                        .build(),
                    MapAnimationOptions.mapAnimationOptions {
                        duration(3000)
                    }
                )

            }

        }

    }
    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize(),
        update = {
            // Configura aquí tu componente de ubicación si los permisos ya están concedidos
            enableLocationComponent(mapView, context)
        }
    )

}

@Composable
fun handleMapClick(point: Point, context: Context) {
    // Aquí manejas el clic en el mapa. Por ejemplo:
    Toast.makeText(
        context,
        "Clic en: Lat=${point.latitude()}, Lon=${point.longitude()}",
        Toast.LENGTH_SHORT
    ).show()
}


//fun StyleExtensionImpl.Builder.Puntos() {
//    val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
//        .withPoint(Point.fromLngLat(18.06, 59.31))
//        .withIconImage(YOUR_ICON_BITMAP)
//        // Make the annotation draggable.
//        .withDraggable(true)
//// Add the draggable pointAnnotation to the map.
//    pointAnnotationManager?.create(pointAnnotationOptions)
//}

fun StyleExtensionImpl.Builder.llenarPuntos(context: Context, lista: List<DtoAlertasItem>) {
    val listafil = lista.filter { it.Fotos != null }
    Log.d("esta es la lista", listafil.toString())
    val features = listafil.flatMap { alerta ->

        alerta.Fotos.map { foto ->

            Feature.fromGeometry(
                Point.fromLngLat(foto.longitud, foto.latitud)
            )
        }
    }

    val featureCollection = FeatureCollection.fromFeatures(features)
    +image(
        "idimage",
        ContextCompat.getDrawable(context, R.drawable.gps1)!!.toBitmap()
    )
    +geoJsonSource("icon") {
        featureCollection(featureCollection)

        // geometry(Point.fromLngLat(-71.974216999999996, -13.523138500000000))
    }
    +symbolLayer("layer", "icon") {
        iconImage("idimage")
        iconAnchor(IconAnchor.BOTTOM)
        iconSize(
            0.3
        )

    }


}

@SuppressLint("IncorrectNumberOfArgumentsInExpression")
fun StyleExtensionImpl.Builder.zonas() {
    +vectorSource(SOURCE_ID) {
        tiles(listOf("https://apicolector.municusco.com/datosabiertos/mvt/pdu2013_diagnostico.tg_pdu2013_diag_ambiental_peligro_remocion_masa/{z}/{x}/{y}?geom_column=geom&precision=9&columns=nivel&id_column=id"))
        minzoom(6)
        maxzoom(14)
    }

    +fillLayer(LAYER_ID, SOURCE_ID) {
        sourceLayer(SOURCE_LAYER_ID)
        fillColor(
            match {
                get("nivel") // Obtiene el valor de la propiedad 'nivel' de los datos
                literal("BAJO")
                rgba(188.0, 255.0, 5.0, 0.7)
                literal("MEDIO")
                rgba(250.0, 247.0, 52.0, 0.7)
                literal("ALTO")
                rgba(255.0, 204.0, 20.0, 0.7)
                literal("MUY ALTO")
                rgba(255.0, 58.0, 23.0, 0.7)
                rgba(200.0, 200.0, 200.0, 0.1)
            }
        )
        fillOutlineColor("rgba(0,0,0,0.3)")
        fillOpacity(0.5)

    }


}


@SuppressLint("IncorrectNumberOfArgumentsInExpression")
fun StyleExtensionImpl.Builder.zonas2() {
    +vectorSource(SOURCE_ID) {
        tiles(listOf("https://apicolector.municusco.com/datosabiertos/mvt/pdu2013_propuesta.tg_pdu2013_prop_zonificacion_full/{z}/{x}/{y}?geom_column=geom&precision=9&columns=zona&id_column=id"))
        minzoom(6)
        maxzoom(14)
    }

    +fillLayer(LAYER_ID, SOURCE_ID) {
        sourceLayer("pdu2013_propuesta.tg_pdu2013_prop_zonificacion_full")
        fillColor(
            match {
                get("zona")
                // Obtiene el valor de la propiedad 'nivel' de los datos
                literal("Z.R.E -SA ARAWAY")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("Z.R.E-SA CONVENTOMOQO")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("Z.R.E -SA LARAPA")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("Z.R.E - SA PATAPATA")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("Z.R.E -ZA MUYO ORQO")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("Z.R.E. ZA SURIWAYLLA")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("Z.R.E -ZA WAYNATAUKARAY")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("Z.R.E -ZA-MACHUTAUKARAY")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("ZA-MACHUTAUKARAY")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("Z.R.E. EX BOTADERO DE SAN ANTONIO")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("Z.R.E -ZA WIMPILLAY")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("SA LETICIA")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA SILLKINA")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA QONTAYMOQO")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA SILLKINCHANI")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA ANDENES DE PUSKAR")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA KALLAMPATA")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA QENCHA")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA CH'EQOLLO")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA HUAYLLAPAMPA")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA PUKAPUKARA")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("SA CHIMARACAY")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("PA SACSAYHUAMAN")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("PA PUMAMARCA")
                rgba(232.0, 165.0, 230.0, 0.7)
                literal("ZRE")
                rgba(144.0, 133.0, 180.0, 0.7)
                literal("ZRP1")
                rgba(158.0, 223.0, 132.0, 0.7)
                literal("ZRP2")
                rgba(158.0, 223.0, 132.0, 0.7)
                literal("RP-4")
                rgba(251.0, 233.0, 175.0, 0.7)
                literal("RP-3")
                rgba(243.0, 238.0, 134.0, 0.7)
                literal("RP-2")
                rgba(249.0, 221.0, 146.0, 0.7)
                literal("R-6")
                rgba(225.0, 193.0, 128.0, 0.7)
                literal("R-5")
                rgba(212.0, 227.0, 104.0, 0.7)
                literal("R-4")
                rgba(231.0, 237.0, 104.0, 0.7)
                literal("R-3")
                rgba(240.0, 227.0, 86.0, 0.7)
                literal("R-2")
                rgba(224.0, 224.0, 163.0, 0.7)
                literal("PU-1")
                rgba(255.0, 191.0, 128.0, 0.7)
                literal("PU-2")
                rgba(255.0, 191.0, 128.0, 0.7)
                literal("C-7")
                rgba(255.0, 77.0, 77.0, 0.7)
                literal("C-5")
                rgba(255.0, 77.0, 77.0, 0.7)
                literal("C-3")
                rgba(255.0, 77.0, 77.0, 0.7)
                literal("C-2")
                rgba(255.0, 77.0, 77.0, 0.7)
                literal("CP-3")
                rgba(255.0, 63.0, 63.0, 0.7)
                literal("E1")
                rgba(112.0, 255.0, 255.0, 0.7)
                literal("E2")
                rgba(112.0, 255.0, 255.0, 0.7)
                literal("E3")
                rgba(112.0, 255.0, 255.0, 0.7)
                literal("E4")
                rgba(112.0, 255.0, 255.0, 0.7)
                literal("H1")
                rgba(251.0, 203.0, 163.0, 0.7)
                literal("H2")
                rgba(251.0, 203.0, 163.0, 0.7)
                literal("AV")
                rgba(128.0, 255.0, 128.0, 0.7)
                literal("OU")
                rgba(127.0, 127.0, 127.0, 0.7)
                literal("I1-R6")
                rgba(255.0, 255.0, 182.0, 0.7)

                literal("I1-R5")
                rgba(255.0, 255.0, 182.0, 0.7)
                literal("I1-R4")
                rgba(255.0, 255.0, 182.0, 0.7)
                literal("I1-R3")
                rgba(255.0, 255.0, 182.0, 0.7)
                literal("I1-RP4")
                rgba(255.0, 255.0, 182.0, 0.7)
                literal("MER")
                rgba(255.0, 46.0, 46.0, 0.7)
                rgba(200.0, 200.0, 200.0, 0.1) // Default color
            }
        )
        fillOutlineColor("rgba(0,0,0,0.3)")
        fillOpacity(0.5)

    }

}

@Composable
fun DynamicMapView(selectedPoint: Point, useSatelliteStyle: Boolean) {
    val context = LocalContext.current

    // Recuerda el MapView y configura el estado inicial
    val mapView = remember {
        MapView(context).apply {
            mapboxMap.setCamera(
                CameraOptions.Builder()
                    .zoom(12.0)
                    .center(selectedPoint)
                    .build()
            )
        }
    }

    // Observa cambios en useSatelliteStyle y actualiza el estilo del mapa
    LaunchedEffect(useSatelliteStyle) {
        mapView.mapboxMap.loadStyleUri(
            if (useSatelliteStyle) Style.SATELLITE_STREETS else Style.MAPBOX_STREETS
        )
    }

    // Observa cambios en selectedPoint y actualiza la posición del centro
    LaunchedEffect(selectedPoint) {
        mapView.mapboxMap.flyTo(
            CameraOptions.Builder()
                .center(selectedPoint)
                .build()
        )
    }

    // AndroidView para incorporar el MapView
    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize()
    )
}


//fun enableLocationComponent(mapView: MapView, style: Style) {
//    if (ContextCompat.checkSelfPermission(mapView.context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//        val locationComponentOptions = LocationComponentOptions.builder(mapView.context)
//            .trackingGesturesManagement(true)
//            .accuracyColor(ContextCompat.getColor(mapView.context, R.color.mapbox_blue))
//            .build()
//
//        val locationComponentActivationOptions = LocationComponentActivationOptions.builder(mapView.context, style)
//            .locationComponentOptions(locationComponentOptions)
//            .build()
//
//        mapView.location.apply {
//            activateLocationComponent(locationComponentActivationOptions)
//            isLocationComponentEnabled = true
//            cameraMode = CameraMode.TRACKING
//            renderMode = RenderModeType.GPS
//        }
//    } else {
//        ActivityCompat.requestPermissions(mapView.context as Activity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
//    }
//}

@SuppressLint("IncorrectNumberOfArgumentsInExpression")
fun enableLocationComponent(mapView: MapView, context: Context) {
    if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        // Configurar el puck de ubicación en 2D
        val locationPuck = LocationPuck2D(
            topImage = ImageHolder.from(R.drawable.gpsp1),
            bearingImage = ImageHolder.from(R.drawable.gpsp2),

            scaleExpression =
            interpolate {
                linear()
                zoom()

                stop {
                    literal(0.0)
                    literal(0.6)
                }
                stop {
                    literal(20.0)
                    literal(0.5)
                }
            }.toJson()
        )

        // Aplicar configuraciones al componente de ubicación
        mapView.location.apply {
            this.locationPuck = locationPuck
            enabled = true
            pulsingEnabled = true
        }
        // startPuckAnimation(mapView,context)
    } else {
        // Solicitar permisos si es necesario
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )
    }
}

@Composable
fun RequestLocationPermission() {
    val context = LocalContext.current
    val activity = (context as? Activity)
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1
                )
            }
        }
    }
}


private fun startPuckAnimation(mapView: MapView, context: Context) {
    val images = listOf(R.drawable.gpsp1, R.drawable.gpsp2) // Tus imágenes
    var index = 0

    val handler = Handler(Looper.getMainLooper())
    val runnable = object : Runnable {
        override fun run() {
            val image = ImageHolder.from(images[index])
            mapView.location.locationPuck = LocationPuck2D(
                bearingImage = image,
                shadowImage = ImageHolder.from(R.drawable.gpsp2), // Asume que tienes una imagen de sombr
                topImage = ImageHolder.from(R.drawable.gpsp2)
            )
            index = (index + 1) % images.size
            handler.postDelayed(this, 600) // Cambia cada 500ms
        }
    }

    handler.post(runnable)
}

