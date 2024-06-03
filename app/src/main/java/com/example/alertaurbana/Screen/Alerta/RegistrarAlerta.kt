package com.example.alertaurbana.Screen.Alerta

import TextDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertaAdd
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Login.ErrorTexto

import com.example.alertaurbana.Screen.Textos.ConstantsAlerta
import com.example.alertaurbana.Screen.UtilCompose.AccesoPref
import com.example.alertaurbana.Screen.UtilCompose.AlertDialogWithTwoButtons
import com.example.alertaurbana.Screen.UtilCompose.VerDia
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.alertaurbana.Navigation.Screen
import com.example.alertaurbana.Screen.Alerta.Contenido.Cards.TakeClosePhotoCard
import com.example.alertaurbana.Screen.Alerta.Contenido.TomarImagen
import com.example.alertaurbana.Screen.Login.MyBottomAppBar
import com.example.alertaurbana.Screen.Login.MyTopAppBar
import com.example.alertaurbana.Screen.Login.MyTopAppBarPop
import com.example.alertaurbana.Screen.UtilCompose.FuncionesExtras


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrarAlerta(
    navController: NavHostController,
    viewModel: AlertaViewmodel = hiltViewModel()
) {
    // State variables for text fields
    var detalleText by rememberSaveable { mutableStateOf("") }
    var contactoText by rememberSaveable { mutableStateOf("") }
    var otrosText by rememberSaveable { mutableStateOf("") }
    var telefonoText by rememberSaveable { mutableStateOf("") }
    var textoDireccion by rememberSaveable { mutableStateOf("") }

    var selectedId by rememberSaveable { mutableStateOf(0) }

    // State variable for the selected item in the ComboBox
    var selectedItem by remember { mutableStateOf("Item 1") }

    // State variable for handling the button click event
    var isButtonClicked by remember { mutableStateOf(false) }
    var existeCoordenada by remember { mutableStateOf(false) }
    var detalleError by remember { mutableStateOf(false) }
    var comboError by remember { mutableStateOf(false) }
    val detalleOtro by remember { mutableStateOf(false) }
    var otros by remember { mutableStateOf(false) }
    var fotos by remember { mutableStateOf(false) }


    var envioAlerta by remember { mutableStateOf(false) }

    // Function to handle button click
    fun onButtonClick() {
        // Perform actions on button click
        isButtonClicked = true
    }

    //val funcionesExtras = FuncionesExtras()
    val context = LocalContext.current


    // viewModel.verGps(context)
    if (viewModel.respuestaObtenerTipos == "ok") {


        val lista = viewModel.ListarLotesList
        var expanded by remember { mutableStateOf(false) }
        var selectedMovie by remember { mutableStateOf(lista[0]) }
        var tipoAlerta by rememberSaveable { mutableStateOf("Seleccione un tipo de alerta") }
        // Compose UI
        FuncionesExtras().LocationSettingsDialog(context)
        Scaffold(
            topBar = {
                //  MyTopAppBar(ConstantsAlerta.ENVIARALERTA)
                MyTopAppBarPop(ConstantsAlerta.ENVIARALERTA) { navController.popBackStack() }
            }
        ) { innerPadding ->

            Box(
                Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .verticalScroll(rememberScrollState())
                ) {
//                    Text(
//                        text = if (isGpsEnabled) "GPS está activado" else "GPS no está activado",
//                        style = MaterialTheme.typography.labelLarge
//                    )


                    // val context = LocalContext.current
                    // Image options with camera icons and buttons


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {


                        // menu box
                        ExposedDropdownMenuBox(


                            expanded = expanded,
                            onExpandedChange = {
                                expanded = !expanded
                            }

                        ) {
                            // textfield
                            OutlinedTextField(
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(), // menuAnchor modifier must be passed to the text field for correctness.
                                readOnly = true,
                                value = tipoAlerta,
                                onValueChange = {},
                                label = {
                                    Row(horizontalArrangement = Arrangement.Start) {
                                        Text("Seleccione un tipo de alerta")
                                        Text(" *", color = Color.Red, fontSize = 20.sp)
                                    }
                                },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded
                                    )
                                },
                                colors = OutlinedTextFieldDefaults.colors(),

                                )

                            ExposedDropdownMenu(
                                expanded = expanded,
                                onDismissRequest = {
                                    expanded = false
                                },
                            ) {
                                // menu items
                                lista.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption.nombre) },
                                        onClick = {
                                            selectedMovie = selectionOption
                                            selectedId = selectedMovie.id
                                            tipoAlerta = selectedMovie.nombre
                                            otros = selectedMovie.nombre == "otros"
                                            expanded = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                    )
                                }
                            }
                        }


                    }
                    if (comboError) {
                        ErrorTexto(
                            "Por favor seleccione un tipo de alerta ",
                            FontWeight.Medium,
                            12.sp
                        )
                    }

                    var miPainter by remember { mutableStateOf<Painter?>(null) }

                    var miPainter2 by remember { mutableStateOf<Painter?>(null) }
                    var miPainter3 by remember { mutableStateOf<Painter?>(null) }


                    TakeClosePhotoCard(
                        "Foto cercana",
                        "Foto cercana  para ubicar las coordenadas de la alerta.",
                        {
                            navController.navigate(Screen.CAMARA.route + "/" + 1)
                            viewModel.imagenVer1 = false
                        },
                        AccesoPref().obtenerimagen1() ?: "",
                        true
                    )
                    if (fotos) {
                        ErrorTexto(
                            "Falta tomar la fotografia ",
                            FontWeight.Medium,
                            12.sp
                        )
                    }
                    TakeClosePhotoCard(
                        "Foto Intermedia",
                        "Foto de donde de muestra la la alerta completa.",
                        {
                            navController.navigate(Screen.CAMARA.route + "/" + 2)
                            viewModel.imagenVer2 = false
                        },
                        AccesoPref().obtenerimagen2() ?: "",
                        false
                    )
                    TakeClosePhotoCard(
                        "Foto panorámica",
                        "Foto donde se muestra la alerta con alguna refrencia cercana para ubicarala.",
                        {
                            navController.navigate(Screen.CAMARA.route + "/" + 3)
                            viewModel.imagenVer3 = false
                        },
                        AccesoPref().obtenerimagen3() ?: "",
                        false
                    )



//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 5.dp),
//                        horizontalArrangement = Arrangement.SpaceAround
//                    ) {
//                        Column {
//                            TomarImagen(120.dp, 100.dp, 100.dp, "Cerca", {
//                                navController.navigate(Screen.CAMARA.route + "/" + 1)
//                                viewModel.imagenVer1 = false
//                            }, AccesoPref().obtenerimagen1() ?: "", true)
//                            if (fotos) {
//                                Box(modifier = Modifier.width(120.dp)) {
//                                    ErrorTexto(
//                                        "Falta foto cercana ",
//                                        FontWeight.Medium,
//                                        12.sp
//                                    )
//                                }
//
//                            }
//                        }
//
//
//                        TomarImagen(120.dp, 100.dp, 100.dp, "Media", {
//                            navController.navigate(Screen.CAMARA.route + "/" + 2)
//                            viewModel.imagenVer2 = false
//                        }, AccesoPref().obtenerimagen2() ?: "", false)
//
//
//                    }


//                    Column(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 3.dp),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        if (AccesoPref().obtenerimagen3() != "") {
//                            miPainter3 =
//                                rememberAsyncImagePainter(convertStringToBitmap(AccesoPref().obtenerimagen3()))
//                        } else {
//                            miPainter3 = painterResource(id = R.drawable.image22)
//                        }
//                        Image(
//                            painter = miPainter3
//                                ?: painterResource(id = R.drawable.image22),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .height(100.dp)
//                                .width(180.dp)
//
//                                .background(MaterialTheme.colorScheme.background)
//                                .clickable {
//                                    navController.navigate(Screen.CAMARA.route + "/" + 3)
//                                    viewModel.imagenVer3 = false
//                                    // Handle image option click
//                                },
//                            contentScale = ContentScale.Fit,
//
//                            )
//
//                        Text("Imagen de panoramica")
//
////                        IconButton(
////                            onClick = {
////                                navController.navigate(Screen.CAMARA.route + "/" + 3)
////                                viewModel.imagenVer3 = false
////                                // Handle camera button click
////                            }
////                        ) {
////                            Icon(
////                                imageVector = Icons.Default.CameraAlt,
////                                contentDescription = "Camera"
////                            )
////                        }
//                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.clickable { navController.navigate(Screen.VERMAPASELECCIONCOORDENADA.route) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Seleccione La Ubicación: (opcional)")
                        Image(
                            painter = painterResource(id = R.drawable.ubicacion),
                            contentDescription = null, // Proporciona una descripción accesible si es necesario
                            modifier = Modifier.size(35.dp) // Ajusta el tamaño según sea necesario
                        )
                        // Image
//                        Icon(
//                                imageVector = Icons.Default.Map,
//                                contentDescription = "Camera",
//                                tint = Color.Red,
//                                modifier= Modifier.clickable { navController.navigate(Screen.VERMAPASELECCIONCOORDENADA.route)     }
//                            )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.Top) {
                        Text("Ubicación:")
                        Log.d("esta aca", viewModel.DireccionAproximadaRespuesta)
                        if (viewModel.respuestaObtenerDireccionesAproximadas != "") {
                            Log.d("esta aca", "es diferente")
                            Text(
                                viewModel.respuestaObtenerDireccionesAproximadas,
                                fontSize = 12.sp,
                                maxLines = 60,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 5.dp)
                            )
                        }


//                        LaunchedEffect(DatosTemporales.datosDireccion) {
//                            Log.d("Esta launch", DatosTemporales.datosDireccion)
//                            textoDireccion=AccesoPref().obtenerDireccionTemporal().toString()
//                        D
//                        DatosTemporales.addSessionIdChangeListener {
//                            Log.d("esta aca",it)
//                            mostrarDatos= true
//
//                        }
//                        if(mostrarDatos)
//                        {
//                            Text(AccesoPref().obtenerDireccionTemporal().toString(), fontSize = 12.sp, maxLines = 60 , overflow = TextOverflow.Ellipsis,modifier= Modifier.padding(horizontal=5.dp))
//                        }
//                        Text(
//                            AccesoPref().obtenerDireccionTemporal().toString(),
//                            fontSize = 12.sp,
//                            maxLines = 60,
//                            overflow = TextOverflow.Ellipsis,
//                            modifier = Modifier.padding(horizontal = 5.dp)
//                        )


//                        if (AccesoPref().obtenerDireccionTemporal()!="")
//                        {
//                            Text(AccesoPref().obtenerDireccionTemporal().toString(), fontSize = 12.sp, maxLines = 60 , overflow = TextOverflow.Ellipsis,modifier= Modifier.padding(horizontal=5.dp))
//                        }

//                        Text(
//                            textoDireccion,
//                            fontSize = 12.sp,
//                            maxLines = 60,
//                            overflow = TextOverflow.Ellipsis,
//                            modifier = Modifier.padding(horizontal = 5.dp)
//                        )


                    }
                    if (existeCoordenada) {
                        ErrorTexto(
                            "Por favor Seleccione Una Ubicación ",
                            FontWeight.Medium,
                            12.sp
                        )
                    }

//        val launcher =
//            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
//                if (activityResult.resultCode == Activity.RESULT_OK) {
//                    capturedImageUri= (activityResult.data?.data ?:"") as Uri
//                    Log.d("dsdsd", activityResult.data?.data?.path.toString() )
//                }
//            }


                    Spacer(modifier = Modifier.height(3.dp))
                    RegisterTexto(
                        ObtenerTexto = { detalleText = it },
                        ConstantsAlerta.DETALLEALERTA,
                        true
                    )
                    if (detalleError) {
                        ErrorTexto(
                            "Por favor ingrese el detalle de la Alerta ",
                            FontWeight.Medium,
                            12.sp
                        )
                    }


                    if (otros) {
                        Spacer(modifier = Modifier.height(3.dp))
                        RegisterNameAlerta(
                            ObtenerTexto = { otrosText = it },
                            ConstantsAlerta.OTROS
                        )

                    }
                    val nombre = AccesoPref().obtenerNombre() ?: "vacio"
                    if (nombre.uppercase() == "ANONIMO") {
                        Spacer(modifier = Modifier.height(3.dp))
                        RegisterNameAlerta(
                            ObtenerTexto = { contactoText = it },
                            ConstantsAlerta.CONTACTO
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        RegisterNameAlerta(
                            ObtenerTexto = { telefonoText = it },
                            ConstantsAlerta.TELEFONO
                        )

                    }

                    Spacer(modifier = Modifier.height(3.dp))
                    // Text fields


                    // ComboBox

                    val gradientColor = listOf(
                        MaterialTheme.colorScheme.primary, // Usa el color primario del tema
                        MaterialTheme.colorScheme.secondary
                    )
                    val cornerRadius = 16.dp
                    // Button to submit data
                    GradientButton2(
                        gradientColors = gradientColor,
                        cornerRadius = cornerRadius,
                        nameButton = "Enviar Alerta",
                        roundedCornerShape = RoundedCornerShape(
                            topStart = 30.dp,
                            bottomEnd = 30.dp
                        )
                    ) {
                        Log.d(
                            "Respuesta Optener coor",
                            viewModel.respuestaObtenerDireccionesAproximadas
                        )

                        existeCoordenada =
                            viewModel.respuestaObtenerDireccionesAproximadas.trim() == ""
                        detalleError = detalleText == ""
                        comboError = selectedId == 0
                        fotos = AccesoPref().obtenerimagen1() == ""

                        Log.d("detalle", detalleText)
                        Log.d("id ", selectedId.toString())
                        Log.d("coordenada ", existeCoordenada.toString())


                        if (!detalleError && !comboError && !fotos && !existeCoordenada) {
                            envioAlerta = true


                            val id = AccesoPref().obtenerId()
                            val hora = VerDia().obtenerHoractualEnFormato()
                            val fecha = VerDia().obtenerFechaActualEnFormato()
                            val dtoAlertaAdd = DtoAlertaAdd(
                                contacto = contactoText,
                                creado_por = id,
                                detalle = detalleText,
                                fecha_alerta = fecha,
                                hora_alerta = hora,
                                otros = otrosText,
                                telefono = telefonoText,
                                tipodealerta = selectedId,
                                usuario = id
                            )
                            viewModel.AgregarAlerta(dtoAlertaAdd)

                        }


                    }
                    if (envioAlerta) {
                        TextDialog {}
                    }

                    if (viewModel.respuestaPostAlerta != "") {
                        envioAlerta = false
                        if (viewModel.respuestaPostAlerta == "ok") {
                            AlertDialogWithTwoButtons({
                                viewModel.respuestaPostAlerta = ""
                                navController.popBackStack()
                            }, "Mesaje", ConstantsAlerta.RESPUESTAOKALERTA)
                        } else {
                            AlertDialogWithTwoButtons({
                                viewModel.respuestaPostAlerta = ""
                                navController.popBackStack()
                            }, "Cuidado", ConstantsAlerta.RESPUESTAERRORALERTA)
                        }


                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }


        }


    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center)
        { CircularProgressIndicator() }

    }

    DisposableEffect(Unit) {
        // Configuración: Mostrar un Toast cuando el composable aparece
        // Toast.makeText(context, "Composable appears", Toast.LENGTH_SHORT).show()
        viewModel.ObtenerDirecciones()
        onDispose {
            // Limpieza: Mostrar otro Toast cuando el composable desaparece
            //  Toast.makeText(context, "Composable disappears", Toast.LENGTH_SHORT).show()
        }
    }


}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterNameAlerta(ObtenerTexto: (String) -> Unit, Texto: String) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            ObtenerTexto(it)
        },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                Texto
            )
        },
        placeholder = { Text(text = Texto) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterTexto(ObtenerTexto: (String) -> Unit, Texto: String, requerido: Boolean) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            ObtenerTexto(it)
        },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {

            Row(horizontalArrangement = Arrangement.Start) {
                Text(Texto)
                if (requerido) {
                    Text(" *", color = Color.Red, fontSize = 20.sp)
                }

            }

        },
        placeholder = {
            Row(horizontalArrangement = Arrangement.Start) {
                Text(Texto)
                if (requerido) {
                    Text(" *", color = Color.Red, fontSize = 20.sp)
                }

            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
        ),
        singleLine = false,
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}

@Composable
private fun GradientButton2(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape,
    onClick: () -> Unit
) {

    androidx.compose.material3.Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        onClick = {
            //your code
            onClick()
        },

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)
                /*.background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius)
                )*/
                .padding(horizontal = 16.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = nameButton,
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}


fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"

    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )


    // Convierte el flujo de entrada en un bitmap


    return image
}

@Throws(IllegalArgumentException::class)
fun convertStringToBitmap(base64Str: String?): Bitmap? {
    val decodedString = Base64.decode(base64Str, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}
