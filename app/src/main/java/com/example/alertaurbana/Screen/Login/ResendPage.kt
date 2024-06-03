package com.example.alertaurbana.Screen.Login


import TextDialog
import android.content.DialogInterface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.EmailRenviarDto
import com.example.alertaurbana.Navigation.AuthScreen
import com.example.alertaurbana.R
import com.example.alertaurbana.Screen.Textos.ConstantsLogin
import com.example.alertaurbana.Screen.UtilCompose.AlertDialogWithTwoButtons

@Composable
fun ResendPage(navController: NavController, viewModel: LoginViewmodel = hiltViewModel()) {
    var emailText by rememberSaveable { mutableStateOf("") }
    val emailError = remember { mutableStateOf(false) }
    var dialogo by remember { mutableStateOf(false) }
    var dialogoAceptar by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                color = Color.Transparent,
            )
    ) {


        Box(
            modifier = Modifier
                /*.background(
                    color = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(25.dp, 5.dp, 25.dp, 5.dp)
                )*/
                .align(Alignment.BottomCenter),
        ) {

            Image(
                painter = painterResource(id = R.drawable.muni_logo_banner_2021),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .height(180.dp)
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
                    text = "Reenviar correo de activación",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 130.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                )

                Spacer(modifier = Modifier.height(8.dp))

                RegisterEmail(ObtenerTexto = { emailText = it }, ConstantsLogin.CORREO)
                if (emailError.value) {
                    ErrorTexto(ConstantsLogin.EMAILERROR, FontWeight.Medium, 12.sp)
                }
                if (viewModel.respuestaReenvioemail != "") {

                    // Text(viewModel.respuestSicreo)
                   // ErrorTexto(viewModel.respuestaReenvioemail, FontWeight.Medium, 12.sp)
                    dialogo = false
                    AlertDialogWithTwoButtons({dialogoAceptar = false},"Alerta",viewModel.respuestaReenvioemail)

                }
                //     ResendEmailID()
                Spacer(modifier = Modifier.padding(3.dp))

                val gradientColor = listOf( MaterialTheme.colorScheme.primary, // Usa el color primario del tema
                    MaterialTheme.colorScheme.secondary )
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
                GradientButtonResend(
                    gradientColors = gradientColor,
                    cornerRadius = cornerRadius,
                    nameButton = "Enviar",
                    roundedCornerShape = RoundedCornerShape(topStart = 30.dp, bottomEnd = 30.dp),

                    ) {
                    viewModel.respuestaReenvioemail= ""
                    if (validarCorreo(emailText)) {
                        emailError.value = false
                        val correo = EmailRenviarDto(
                            email = emailText
                        )
                        dialogo = true

                        viewModel.reenviarCorreo(correo)

                    } else {
                        emailError.value = true
                    }


                }
                if (dialogo) (
                        TextDialog {

                        }
                        )

                Spacer(modifier = Modifier.padding(10.dp))
                TextButton(onClick = {

                    navController.navigate(AuthScreen.Login.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }

                }) {
                    Text(
                        text = "Inicio de Sesión",
                        letterSpacing = 1.sp,
                        style = MaterialTheme.typography.labelLarge
                    )
                }

                Spacer(modifier = Modifier.padding(5.dp))


            }


        }

    }


}


//...........................................................................
@Composable
private fun GradientButtonResend(
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
            androidx.compose.material3.Text(
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
fun ResendEmailID() {
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by rememberSaveable { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp),
        label = {
            Text(
                "Ingrese un Email Registrado",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.labelMedium,
            )
        },
        placeholder = { Text(text = "Ingrese un Email Registrado") },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
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
