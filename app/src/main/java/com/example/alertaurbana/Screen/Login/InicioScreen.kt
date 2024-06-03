package com.example.alertaurbana.Screen.Login

import android.Manifest
import android.content.Context
import android.provider.SyncStateContract.Constants
import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.alertaurbana.Screen.UtilCompose.AccesoPref


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AcUnit
import androidx.compose.material.icons.filled.AddAlert
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Brightness5
import androidx.compose.material.icons.filled.Pageview

import androidx.compose.material3.*


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import com.example.alertaurbana.Agregarpermisos
import com.example.alertaurbana.Navigation.AuthScreen
import com.example.alertaurbana.Navigation.Screen
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Login.componentes.IconosPantalla
import com.example.alertaurbana.Screen.Textos.ConstantsAlerta
import com.example.alertaurbana.Screen.UtilCompose.LocationService
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun InicioScren(viewModel: LoginViewmodel = hiltViewModel(), navController: NavHostController) {
    val datos = AccesoPref()
    var LocationText by rememberSaveable { mutableStateOf("") }
    val locationService: LocationService = LocationService()
    val context = LocalContext.current
    val locationPermissionState = rememberPermissionState(
        permission = Manifest.permission.ACCESS_FINE_LOCATION
    )

    LaunchedEffect(key1 = true) {
        locationPermissionState.launchPermissionRequest()
    }

    Scaffold(
        topBar = { MyTopAppBar(ConstantsAlerta.TITULOALERTA) },

        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Imagen superior central

                    Image(
                        painter = painterResource(id = R.drawable.llamaconmegafono3), // Reemplaza con tu imagen
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(shape = MaterialTheme.shapes.medium)

                    )


                    Text(text = "USUARIO:" , fontSize = 10.sp, fontWeight = FontWeight.Light , color=MaterialTheme.colorScheme.secondary)
                    datos.obtenerNombre()?.let { Text(it.uppercase() ,fontSize = 14.sp, fontWeight = FontWeight.Bold,color=MaterialTheme.colorScheme.secondary) }

                    // Espaciado
                    Spacer(modifier = Modifier.height(20.dp))
                    if (viewModel.coordenadas != "") {
                        Text(text=viewModel.coordenadas)
                    }
                    // Fila de tres iconos
                    Spacer(modifier = Modifier.height(20.dp))

                    Card(
                        modifier = Modifier
                            .padding(0.dp)
                            .fillMaxHeight(), // Añade un padding alrededor de la Card para la sombra
                        shape = MaterialTheme.shapes.extraLarge, // Usa el tema de formas para bordes redondeados
                        elevation = CardDefaults.cardElevation(10.dp),// Establece la elevación para la sombra
                        colors = CardDefaults.cardColors(Color.White)
                    ) {
                        Column {
                            Box(modifier = Modifier.padding(16.dp)) {
                                // Contenido de la Card
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
//
                                    IconosPantalla(navController ,"Envie Una Alerta",painterResource(id = R.drawable.megafonoqxdd),Screen.AGREGARALERTAS.route,0xFFF0EAE2)
                                    if (viewModel.locationString == "false") {
                                        Toast.makeText(
                                            context,
                                            ConstantsAlerta.ALERTADEGPS,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        viewModel.locationString = ""
                                    }
                                    IconosPantalla(navController ,"Notificaciones",painterResource(id = R.drawable.campana),Screen.VERNOTIFICACIONES.route,0xFFF0EAE2)
                                    IconosPantalla(navController ,"Mapas de riesgos",painterResource(id = R.drawable.mapa2),Screen.VERMAPAZONAS.route,0xFFF0EAE2)


                                }


                            }

                            Box(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceAround
                                ) {
//
                                    IconosPantalla(
                                        navController,
                                        "Testtero",
                                        painterResource(id = R.drawable.megafono_pintado),
                                        Screen.TESTSCREEN.route,
                                        0xFFF0EAE2
                                    )


                                }
                            }
                        }



                    }

                }
            }

        }
    )
    //datos.obtenerNombre()?.let { Text(it) }

    DisposableEffect(Unit) {
        // Configuración: Mostrar un Toast cuando el composable aparece
       // Toast.makeText(context, "Composable appears", Toast.LENGTH_SHORT).show()
        AccesoPref().llenarImagen1("")
        AccesoPref().llenarImagen2("")
        AccesoPref().llenarImagen3("")
        AccesoPref().llenarlatitud("")
        AccesoPref().llenarlongitud("")
        AccesoPref().llenarlatitud2("")
        AccesoPref().llenarlongitud2("")
        AccesoPref().llenarlatitud3("")
        AccesoPref().llenarlongitud3("")
        AccesoPref().llenarDireccionTemporal("")
        onDispose {
            // Limpieza: Mostrar otro Toast cuando el composable desaparece
          //  Toast.makeText(context, "Composable disappears", Toast.LENGTH_SHORT).show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(titulo: String) {
    TopAppBar(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        title = {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp), contentAlignment = Alignment.Center) {
                Row() {
                    Text(
                        text = titulo,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
    )
}
//Icon(
//                            tint = Color.White,
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "Regresar",
//                            modifier = Modifier.clickable {
//                                navController.popBackStack()
//                            })

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBarPop(titulo: String,   click: () -> Unit) {
    var isClicked by remember { mutableStateOf(false) }
    TopAppBar(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth(),
        title = {

            Box(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp), contentAlignment = Alignment.Center) {
                Row(modifier= Modifier.fillMaxSize(), horizontalArrangement=Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                            tint = Color.White,
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            modifier = Modifier.clickable {
                                if (!isClicked) {
                                    isClicked = true  // Cambiar el estado a true después del primer clic
                                    click()
                                }
                            })
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(modifier= Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart
                    ){
                        Text(
                            text = titulo,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }

                }

            }
        },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ),
    )
}

@Composable
fun MyBottomAppBar() {
    BottomAppBar(
        modifier = Modifier.height(25.dp),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.White
    ) {
        // Aquí puedes agregar los íconos y acciones para tu BottomAppBar

    }
}

//@Composable
//fun MyContent(paddingValues: PaddingValues) {
//    // Tu contenido principal aquí, con el padding proporcionado por el Scaffold
//    // para no solapar con las barras superior e inferior
//    Text(text = "Contenido principal", modifier = Modifier.padding(paddingValues))
//}