package com.example.alertaurbana.Screen.Login


import TextDialog
import android.util.Log
import android.util.Size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.UsuarioEnvioDto
import com.example.alertaurbana.Navigation.AuthScreen
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Textos.ConstantsAlerta
import com.example.alertaurbana.Screen.Textos.ConstantsLogin
import com.example.alertaurbana.Screen.UtilCompose.AlertDialogWithTwoButtons
import java.time.Instant
import java.time.ZoneId

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavController, viewModel: LoginViewmodel = hiltViewModel()) {
    var nombreText by rememberSaveable { mutableStateOf("") }
    var apellidosText by rememberSaveable { mutableStateOf("") }
    var dniText by rememberSaveable { mutableStateOf("") }
    var anioText by rememberSaveable { mutableStateOf("") }
    var anio2Text by rememberSaveable { mutableStateOf("") }
    var telefonoText by rememberSaveable { mutableStateOf("") }
    var celularText by rememberSaveable { mutableStateOf("") }
    var emailText by rememberSaveable { mutableStateOf("") }
    val state = rememberDatePickerState()
    val showDialog = remember { mutableStateOf(false) }
    var passwordText by rememberSaveable { mutableStateOf("") }
    var repasswordText by rememberSaveable { mutableStateOf("") }

    val nombreError = remember { mutableStateOf(false) }
    val apellidosError = remember { mutableStateOf(false) }
    val dniError = remember { mutableStateOf(false) }
    val anio2Error = remember { mutableStateOf(false) }
    val celularError = remember { mutableStateOf(false) }
    val emailError = remember { mutableStateOf(false) }
    val passwordError = remember { mutableStateOf(false) }
    val repasswordError = remember { mutableStateOf(false) }

    val errorGeneral = remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    var dialogo by remember { mutableStateOf(false) }
    var dialogoAceptar by remember { mutableStateOf(false) }

    val backgroundImage: Painter = painterResource(id = R.drawable.muni_logo_banner_2021)
    Scaffold(
        //   topBar = { MyTopAppBar(ConstantsAlerta.TITULOALERTA) },

        content = { innerPadding ->


            Box(
                modifier = Modifier
                    .fillMaxSize()
                 //.verticalScroll(scrollState )
                    .background(
                        color = MaterialTheme.colorScheme.background,

                        )
                  .padding(innerPadding)

            ) {


                Column(
                    modifier = Modifier

                        .fillMaxSize()
                       .verticalScroll(scrollState),

                    horizontalAlignment = Alignment.CenterHorizontally,
                   // verticalArrangement = Arrangement.Center
                ) {
//                Image(
//                painter = painterResource(id = R.drawable.cusco),
//                contentDescription = null,
//                contentScale = ContentScale.Fit,
//                modifier = Modifier
//                    .height(50.dp)
//                    .fillMaxWidth(),
//
//                )

                    //.........................Spacer
                    Spacer(modifier = Modifier.height(20.dp))

                    //.........................Text: title
                    Text(
                        text = ConstantsLogin.CREARUSUARIO,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(top = 1.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primaryContainer,
                    )
                    Spacer(modifier = Modifier.height(3.dp))

                    RegisterNamenumberDni(ObtenerTexto = { dniText = it }, ConstantsLogin.DNI)
                    if (dniError.value) {
                        ErrorTexto(ConstantsLogin.ERRORDNI, FontWeight.Medium, 12.sp)
                    }

                    Spacer(modifier = Modifier.height(3.dp))
                    RegisterName(ObtenerTexto = { nombreText = it }, ConstantsLogin.NOMBRES)
                    if (nombreError.value) {
                        ErrorTexto(ConstantsLogin.ERRORNOMBRES, FontWeight.Medium, 12.sp)
                    }

                    Spacer(modifier = Modifier.height(3.dp))
                    RegisterName(ObtenerTexto = { apellidosText = it }, ConstantsLogin.APELLIDOS)
                    if (apellidosError.value) {
                        ErrorTexto(ConstantsLogin.ERRORAPELLIDOS, FontWeight.Medium, 12.sp)
                    }


                    Spacer(modifier = Modifier.height(3.dp))
                    RegisterEmail(ObtenerTexto = { emailText = it }, ConstantsLogin.CORREO)
                    if (emailError.value) {
                        ErrorTexto(ConstantsLogin.EMAILERROR, FontWeight.Medium, 12.sp)
                    }


//                Spacer(modifier = Modifier.padding(3.dp))
//                RegisterPhone(ObtenerTexto = { telefonoText = it }, ConstantsLogin.TELEFONOS)

                    Spacer(modifier = Modifier.height(3.dp))
                    RegisterPhone(ObtenerTexto = { celularText = it }, ConstantsLogin.CELULAR)
                    if (celularError.value) {
                        ErrorTexto(ConstantsLogin.ERRORCELULAR, FontWeight.Medium, 12.sp)
                    }


                    Spacer(modifier = Modifier.padding(3.dp))

                    RegisterFecha(
                        anioText,
                        {
                            showDialog.value = true

                        }, Texto = "Fecha de Nacimiento"
                    )
                    if (anio2Error.value) {
                        ErrorTexto(ConstantsLogin.ERRORFECHA, FontWeight.Medium, 12.sp)
                    }

                    if (showDialog.value) {
                        DatePickerDialog(
                            onDismissRequest = {
                                showDialog.value = false
                            }, confirmButton = {
                                Button(onClick = { showDialog.value = false }) {
                                    Text("Aceptar")
                                }
                            }) {
                            DatePicker(state = state)
                        }
                    }
                    val date = state.selectedDateMillis
                    date?.let {

                        val dateLocal =
                            Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
                        anioText = "${String.format("%02d", dateLocal.dayOfMonth)}/${
                            String.format(
                                "%02d",
                                dateLocal.monthValue
                            )
                        }/${dateLocal.year} "

                        anio2Text = "${dateLocal.year}-${
                            String.format(
                                "%02d",
                                dateLocal.monthValue
                            )
                        }-${String.format("%02d", dateLocal.dayOfMonth)}"
                    }




                    Spacer(modifier = Modifier.height(3.dp))
                    RegisterPassword(
                        ObtenerTexto = { passwordText = it },
                        ConstantsLogin.INGRESESUCONTRASENA
                    )
                    if (passwordError.value) {
                        ErrorTexto(ConstantsLogin.ERRORCONTRASENA, FontWeight.Medium, 12.sp)
                    }

                    Spacer(modifier = Modifier.height(3.dp))
                    RegisterPassword(
                        ObtenerTexto = { repasswordText = it },
                        ConstantsLogin.CONFIRMARCONTRASENA
                    )


                    if (repasswordError.value) {
                        ErrorTexto(ConstantsLogin.RERRORCONTRASENA, FontWeight.Medium, 12.sp)
                    }




                    Spacer(modifier = Modifier.height(5.dp))
                    /* Button(
                         onClick = {},
                         modifier = Modifier
                             .fillMaxWidth(0.8f)
                             .height(50.dp)
                     ) {
                         Text(text = "Login", fontSize = 20.sp)
                     }*/
                    if (viewModel.respuestSicreo != "") {

                        // Text(viewModel.respuestSicreo)
                        dialogo = false
                        AlertDialogWithTwoButtons({
                            dialogoAceptar = false
                            if (viewModel.respuestaSiesAgregadoCorrectamente) {
                                navController.popBackStack()
                            }
                        }, "Alerta", viewModel.respuestSicreo)
                    }
                    //val gradientColor = listOf(Color(0xFFFF8A80), Color(0xffff4c41))


                    val gradientColor = listOf(
                        MaterialTheme.colorScheme.primary, // Usa el color primario del tema
                        MaterialTheme.colorScheme.secondary
                    )
                    val cornerRadius = 16.dp

                    GradientButton(
                        gradientColors = gradientColor,
                        cornerRadius = cornerRadius,
                        nameButton = ConstantsLogin.CREARUSUSARIO,
                        roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp)
                    ) {
                        var error = 0
                        Log.d("Concatenar", "$nombreText + $apellidosText")
                        if (celularText.trim() == "") {
                            celularError.value = true
                            error = 0
                        } else {
                            celularError.value = false

                            error += 1
                        }
                        if (dniText.trim() == "" || dniText.length < 8 || dniText.length > 8) {
                            dniError.value = true

                            error = 0
                        } else {
                            dniError.value = false
                            error += 1

                        }
                        if (anio2Text.trim() == "") {
                            anio2Error.value = true
                            error = 0
                        } else {

                            anio2Error.value = false
                            error += 1
                        }

                        if (nombreText.trim() == "") {
                            nombreError.value = true

                            error = 0
                        } else {
                            nombreError.value = false
                            error += 1
                        }
                        if (apellidosText.trim() == "") {
                            apellidosError.value = true
                            error = 0

                        } else {
                            apellidosError.value = false
                            error += 1
                        }

                        if (validarCorreo(emailText)) {
                            emailError.value = false

                            error += 1
                        } else {
                            emailError.value = true
                            error = 0
                        }
                        Log.d("sds", error.toString())
                        if (validarPassword(passwordText)) {
                            passwordError.value = false
                            error += 1
                        } else {
                            error = 0
                            passwordError.value = true
                        }
                        if (passwordText == repasswordText) {
                            error += 1
                            repasswordError.value = false
                        } else {
                            error = 0
                            repasswordError.value = true
                        }

                        if (error == 8) {
                            viewModel.respuestSicreo = ""
                            dialogo = true
                            val usuario = UsuarioEnvioDto(
                                celular = celularText.trim(),
                                dni = dniText.trim(),
                                email = emailText.trim(),
                                esuser_interno = false,
                                fecha_nacimiento = anio2Text.trim(),
                                first_name = nombreText.trim(),
                                last_name = apellidosText.trim(),
                                password = passwordText.trim(),
                                telefono = "",
                                username = dniText.trim()
                            )

                            viewModel.agregarNuevo(usuario)


                        }


                    }
//                    if (dialogo) (
//                            TextDialog {
//
//                            }
//                            )

                    Spacer(modifier = Modifier.height(2.dp))
                    TextButton(onClick = {
                        navController.navigate(AuthScreen.Login.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }

                    }) {
                        Text(
                            text = ConstantsLogin.INICIARSESION,
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }


                //    Spacer(modifier = Modifier.padding(3.dp))
                    TextButton(onClick = {

                        navController.navigate(AuthScreen.Resend.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }


                    }) {
                        Text(
                            text = ConstantsLogin.VOLVERAENVIARACTIVACION,
                            letterSpacing = 1.sp,
                            style = MaterialTheme.typography.labelLarge,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }


            }

        })
}

fun validarCorreo(correo: String): Boolean {
    val patronCorreo = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

    return patronCorreo.matches(correo)
}

fun validarPassword(password: String): Boolean {
    val regex = Regex("^(?=.*[A-Z])(?=.*\\d).{8,}\$")
    return regex.matches(password)
}

@Composable
fun ErrorTexto(texto: String, fontWeight: FontWeight, fontSize: TextUnit) {
    Text(
        modifier = Modifier
            .padding(start = 40.dp, end = 15.dp)
            .fillMaxWidth(),
        text = texto,
        color = Color.Red,
        fontWeight = fontWeight,
        fontStyle = FontStyle.Italic,
        fontSize = fontSize,
        lineHeight = 10.sp,
        textAlign = TextAlign.Left

    )


}

//...........................................................................
@Composable
private fun GradientButton(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape,
    onClick: () -> Unit
) {

    Button(
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


//name

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterName(ObtenerTexto: (String) -> Unit, Texto: String) {
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
                Texto,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium,
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
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
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
fun RegisterNamenumber(ObtenerTexto: (String) -> Unit, Texto: String) {
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
                Texto,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        placeholder = { Text(text = Texto) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
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
fun RegisterNamenumberDni(ObtenerTexto: (String) -> Unit, Texto: String) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = {
            if (it.length <= 8) {
                text = it
                ObtenerTexto(it)
            }
        },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                Texto,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        placeholder = { Text(text = Texto) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Number
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
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
fun RegisterFecha(TextoMostrar: String, click: () -> Unit, Texto: String) {
    Log.d("registrarFecha", TextoMostrar)
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        readOnly = true,
        value = TextoMostrar,
        onValueChange = {
//            text = it
//            ObtenerTexto(it)
        },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                Texto,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium,

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
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        ),
        trailingIcon = {
            IconButton(onClick = { click() }) {

                Icon(
                    imageVector = Icons.Filled.CalendarMonth,
                    contentDescription = "calendar"
                )
            }
        }


    )
}


//phone
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterPhone(ObtenerTexto: (String) -> Unit, Texto: String) {
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
                Texto,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        placeholder = { Text(text = Texto) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Phone
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}


//email id
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegisterEmail(ObtenerTexto: (String) -> Unit, Texto: String) {
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
                Texto,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        placeholder = { Text(text = Texto) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        ),
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )

    )
}

//password
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegisterPassword(ObtenerTexto: (String) -> Unit, Texto: String) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    OutlinedTextField(
        value = password,
        onValueChange = {
            password = it
            ObtenerTexto(it)
        },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                Texto,
                color = MaterialTheme.colorScheme.outline,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        ),
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Visibility else VisibilityOff
                // Please provide localized description for accessibility services
                val description =
                    if (passwordHidden) "Mostrar contrseña" else "ocultar contraseña"
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        },
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )
    )
}

//password confirm
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun RegisterPasswordConfirm() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                "Confirm Password",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        visualTransformation =
        if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        //  keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary,
            unfocusedTextColor = MaterialTheme.colorScheme.secondary
        ),
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Visibility else VisibilityOff
                // Please provide localized description for accessibility services
                val description =
                    if (passwordHidden) "Mostrar contraseña" else "ocultar contraseña"
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        },
        modifier = Modifier.fillMaxWidth(0.8f),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        )
    )
}