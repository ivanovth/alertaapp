package com.example.alertaurbana.Screen.Login

import TextDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.UsuarioEnvioDto
import com.example.alertaurbana.Navigation.AuthScreen
import com.example.alertaurbana.Navigation.Screen
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Textos.ConstantsLogin
import com.example.alertaurbana.Screen.UtilCompose.AlertDialogWithTwoButtons
import java.security.KeyStore.TrustedCertificateEntry


@Composable
fun LoginPage(navController: NavController, viewModel: LoginViewmodel = hiltViewModel()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.Transparent,
                    )
            ) {
                var usuarioText by rememberSaveable { mutableStateOf("") }
                var passwordText by rememberSaveable { mutableStateOf("") }
                var esperaDialog by rememberSaveable { mutableStateOf(false) }
                var dialogpAceptat by rememberSaveable { mutableStateOf(false) }

                val dniError = remember { mutableStateOf(false) }
                val passwordError = remember { mutableStateOf(false) }


                Box(
                    modifier = Modifier
                        /*.background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                )*/
                        .align(Alignment.BottomCenter),
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.cusco),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(240.dp)
                            .fillMaxWidth(),

                        )
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),

                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        //.........................Spacer
                        Spacer(modifier = Modifier.height(50.dp))

                        //.........................Text: title
                        Text(
                            text = ConstantsLogin.INICIARSESION,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(top = 130.dp)
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        //    SimpleOutlinedTextFieldSample()
                        RegisterNamenumberDni(
                            ObtenerTexto = { usuarioText = it },
                            ConstantsLogin.DNI
                        )
                        if (dniError.value) {
                            ErrorTexto(ConstantsLogin.ERRORDNI, FontWeight.Medium, 10.sp)
                        }
                        Spacer(modifier = Modifier.padding(3.dp))
                        //    SimpleOutlinedPasswordTextField()
                        RegisterPassword(
                            ObtenerTexto = { passwordText = it },
                            ConstantsLogin.INGRESESUCONTRASENA
                        )
                        if (passwordError.value) {
                            ErrorTexto(ConstantsLogin.ERRORCONTRASENA, FontWeight.Medium, 10.sp)
                        }


                        val gradientColor = listOf(
                            MaterialTheme.colorScheme.primary, // Usa el color primario del tema
                            MaterialTheme.colorScheme.secondary
                        )
                        val cornerRadius = 16.dp


                        Spacer(modifier = Modifier.padding(10.dp))
                        /* Button(
                     onClick = {},
                     modifier = Modifier
                         .fillMaxWidth(0.8f)
                         .height(50.dp)
                 ) {
                     Text(text = "Login", fontSize = 20.sp)
                 }*/
                        GradientButton(
                            gradientColors = gradientColor,
                            cornerRadius = cornerRadius,
                            nameButton = ConstantsLogin.INICIARSESION,
                            roundedCornerShape = RoundedCornerShape(
                                topStart = 30.dp,
                                bottomEnd = 30.dp
                            ),

                            ) {
                            dniError.value = usuarioText.length < 8

                            passwordError.value = passwordText == ""
                            if (!passwordError.value && !dniError.value) {
                                viewModel.obtenerToken(usuarioText.trim(), passwordText.trim())
                                esperaDialog = true
                            }


                        }
                        if (esperaDialog) {
                            TextDialog {}
                        }
                        if (viewModel.respuestaAfirmativa) {
                            esperaDialog = false
                            navController.popBackStack()
                            navController.navigate(Screen.InicioScreen.route)
                            Log.d("Estoy aca", "acaaaa")
                        }

                        if (viewModel.respuestaToken != "" || viewModel.respuestaMeusuario != "") {

                            // Text(viewModel.respuestSicreo)
                            esperaDialog = false
                            AlertDialogWithTwoButtons(
                                {
                                    dialogpAceptat = false
                                    viewModel.respuestaToken = ""
                                    viewModel.respuestaMeusuario = ""
                                },
                                "Alerta",
                                "${viewModel.respuestaToken}/n ${viewModel.respuestaMeusuario}  "
                            )
                        }

                        Spacer(modifier = Modifier.padding(10.dp))
                        TextButton(onClick = {

                            navController.navigate(AuthScreen.SignUp.route)
//                    {
//                        popUpTo(navController.graph.startDestinationId)
//                        launchSingleTop = true
//                    }

                        }) {
                            Text(
                                text = ConstantsLogin.CREARUSUARIO,
                                letterSpacing = 1.sp,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                        Spacer(modifier = Modifier.padding(6.dp))
                        TextButton(onClick = {
                            viewModel.obtenerToken("99999999", "1Desconocido")
                            esperaDialog = true
                            //   navController.navigate(AuthScreen.SignUp.route)
//                    {
//                        popUpTo(navController.graph.startDestinationId)
//                        launchSingleTop = true
//                    }

                        }) {
                            Text(
                                text = ConstantsLogin.DENUNCIAANONIMA,
                                letterSpacing = 1.sp,
                                style = MaterialTheme.typography.labelLarge
                            )
                        }




                        Spacer(modifier = Modifier.padding(5.dp))
                        TextButton(onClick = {

                            navController.navigate(AuthScreen.Forgot.route)
//                    {
//                        popUpTo(navController.graph.startDestinationId)
//                        launchSingleTop = true
//                    }

                        }) {
                            Text(
                                text = ConstantsLogin.RESETEARCONTRASENA,
                                letterSpacing = 1.sp,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                       Spacer(modifier = Modifier.padding(20.dp))

                    }


                }

            }


}


//...........................................................................
@Composable
private fun GradientButton(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape,
    click: () -> Unit
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 32.dp, end = 32.dp),
        onClick = {
            //your code
            click()
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


//email id
@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SimpleOutlinedTextFieldSample() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        textStyle= LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.primary),
        value = text,
        onValueChange = { text = it },


        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                ConstantsLogin.USUARIOCORREOELECTRONICO,
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        placeholder = { Text(text = ConstantsLogin.USUARIOCORREOELECTRONICO) },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
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
fun SimpleOutlinedPasswordTextField() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var password by rememberSaveable { mutableStateOf("") }
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                ConstantsLogin.INGRESESUCONTRASENA,
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

        ),
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Visibility else VisibilityOff
                // Please provide localized description for accessibility services
                val description =
                    if (passwordHidden) ConstantsLogin.MOSTRARCONTRASENA else ConstantsLogin.OCULTARCONTRASENA
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