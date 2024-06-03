package com.example.alertaurbana.Screen.Login.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.alertaurbana.Navigation.Screen
import com.example.alertaurbana.R

@Composable
fun IconosPantalla(  navController:NavController,titulo: String, painter: Painter,route : String,fondo:Long){





        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier=Modifier.width(100.dp).padding(top = 10.dp)) {
            IconButton(
                onClick = {
                   navController.navigate(route)

                },
                modifier = Modifier.size(80.dp)
            ) {
                Box (


                    modifier = Modifier
                        .size(80.dp) // Tamaño del círculo
                        .clip(CircleShape) // Esto recorta el Box en forma de círculo
                        .background(Color(fondo)),
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        painter = painter,
                        contentDescription = null, // Proporciona una descripción accesible si es necesario
                        modifier = Modifier.size(40.dp) // Ajusta el tamaño según sea necesario
                    )
                }

            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(titulo, fontSize = 12.sp, textAlign = TextAlign.Center,  color=
                MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
        }


}
//@Preview(showBackground = true)
//@Composable
//fun Ver(){
//    IconosPantalla( titulo = "Alerta", painter = painterResource(id = R.drawable.megafonoqxdd))
//}