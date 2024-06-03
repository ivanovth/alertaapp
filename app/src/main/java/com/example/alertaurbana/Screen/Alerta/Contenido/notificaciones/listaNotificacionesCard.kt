package com.example.alertaurbana.Screen.Alerta.Contenido.notificaciones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoNotificaciones
import com.example.alertaurbana.Screen.Alerta.AlertaViewmodel
import com.example.alertaurbana.Screen.Textos.ConstantsAlerta.Companion.FECHA

import com.example.alertaurbana.Screen.Textos.ConstantsAlerta.Companion.TITULONOTIFICACION

@Composable
fun NotificacionesCard(
    navController: NavHostController = rememberNavController(),
    notificaciones: DtoNotificaciones,
    fichaLotes: List<DtoNotificaciones>,
    viewModel:ViewModel

    //  deleteMascota: () -> Unit,
    //  navigateToUpdateMascotaScreen: (mascotaId: Int) -> Unit
) {
    val context = LocalContext.current



//    val datosUbigeo = if (fichaLote.codigo.length > 6) {
//        ObtenerDatosUbigeo(context, fichaLote.codigo.substring(0, 6))
//    } else {
//        ObtenerDatosUbigeo(context, "0801031006000460000100".substring(0, 6))
//    }
//
//    var color = if (fichaLote.estado && !fichaLote.esta_dividido && !fichaLote.esta_unido && fichaLote.lote_division==""  && fichaLote.lote_fusion=="" && fichaLote.estado_asignacion=="ASIGNADO") {
//        colorResource(id = R.color.LoteAsignaciondelote)
//
//    } else if (fichaLote.estado && !fichaLote.esta_dividido && !fichaLote.esta_unido && fichaLote.lote_division==""  && fichaLote.lote_fusion=="" && fichaLote.estado_asignacion=="PENDIENTE") {
//        colorResource(id = R.color.LoteLLenadoFicha)
//    } else  {
//        colorResource(id = R.color.LoteLLenadoFicha)
//    }
//
//    if (fichaLote.esnuevo && fichaLote.lote_division!="") {
//        color = colorResource(id = R.color.LoteDividido)
//    }
//
//    if (fichaLote.esnuevo && fichaLote.lote_fusion!="") {
//        color = colorResource(id = R.color.LoteFusionado)
//    }

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(3.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column() {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {

                    Row() {
                        Text("$TITULONOTIFICACION: ", fontWeight = FontWeight.SemiBold)
                        Text(notificaciones.TituloNotificacion)
                        Spacer(
                            modifier = Modifier.width(5.dp)
                        )
                        Text("$FECHA: ", fontWeight = FontWeight.SemiBold)
                        Text(notificaciones.fechaNotificacion)

                    }


                }
                Spacer(
                    modifier = Modifier.weight(0.1f)
                )


            }
            HorizontalDivider(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 1.dp, horizontal = 10.dp)
            )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(1.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                if (fichaLote.estado) {
//                    VerFicha(
//                        navController, fichaLote,
//                        //   modificarLote = {ficha->viewModel.updateFichaLote(ficha)}
//                    )
//                } else {
//                    VerNulo()
//                }
//
//
//                Spacer(
//                    modifier = Modifier.weight(0.5f)
//                )
//                Mapaicon(
//
//                    fichaLote.latitud, fichaLote.longitud,fichaLote.idusuario
//
//                )
//                Spacer(
//                    modifier = Modifier.weight(0.5f)
//                )
//
//                if (fichaLote.esnuevo) {
//                    VerNulo()
//
//                }
//                else {
//                    if (fichaLote.estado_asignacion=="PENDIENTE")
//                    {
//                        VerNulo()
//                    }
//                    else {
//                        SubdividirLote(
//                            fichaLote,
//                            viewModel
//
//                        )
//                    }
//                }
//                Spacer(
//                    modifier = Modifier.weight(0.5f)
//                )
//                MostrarShhet(fichaLotes, viewModel,fichaLote)
//
//
//            }

        }

    }
}