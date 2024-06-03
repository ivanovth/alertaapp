package com.example.alertaurbana.Screen.Alerta.Contenido.notificaciones

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.navigation.NavHostController
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoNotificaciones

import com.example.alertaurbana.Screen.Alerta.Contenido.ViewModels.NotificacionesViewmodel

@Composable
fun NotificacionesContent(
    navController: NavHostController,
    notificacionesList: List<DtoNotificaciones>,
    viewModel: NotificacionesViewmodel

    //  padding : PaddingValues,
    //  deleteMascota: (mascota:Mascota) -> Unit,
    // navigateToUpdateMascotaScreen: (mascotaId: Int) -> Unit
) {
//    val listaOrdenada = fichaLotes.sortedWith(compareBy(
//        { it.fechaNotificacion },   // Primero, ordena por la propiedad 'manzana'
//         // Luego, ordena por la propiedad 'lote'
//    )).filter { it.estado_asignacion!="COMPLETO" && it.estado_asignacion!="UNIDO" && it.estado_asignacion!="DIVIDIDO"}
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
        //  .padding(padding)
    ) {
        items(items = notificacionesList) { notificaciones->
            NotificacionesCard(
                navController,
                notificaciones= notificaciones,
                notificacionesList,
                viewModel

            )
        }
    }
}
