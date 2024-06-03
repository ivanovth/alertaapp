package com.example.alertaurbana.Screen.Alerta.Contenido.ViewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alertaurbana.Datos.remote.AlertaInterface
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoNotificaciones
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoTipoAlertaItem
import com.example.alertaurbana.Datos.repository.TipoAlerta_Repository
import com.example.alertaurbana.Screen.UtilCompose.AccesoPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificacionesViewmodel @Inject constructor(
    private val repo: TipoAlerta_Repository
) : ViewModel() {
    val preferencias = AccesoPref()

    var ListarLotesList: List<DtoTipoAlertaItem> = listOf()

    var ListarNotificacionesList: List<DtoNotificaciones> = listOf()
    var estallenadoTiposdeAlerta by mutableStateOf(false)

    var respuestaObtenerNotificaciones by mutableStateOf("")


    fun ObtenerNotificaciones() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("array", "it[0].nombre")
        repo.getNotificaciones(preferencias.obtenerToken() ?: "nada", AlertaInterface.instance)
            .onSuccess {
                ListarNotificacionesList = it
                Log.d("array", it[0].DetalleNotificacion)

                //    prueba = it[0].toString()
                respuestaObtenerNotificaciones = "ok"
            }.onFailure {
                respuestaObtenerNotificaciones = it.message.toString()
                Log.d("error", "feoo")
            }


//        respuestaObtenerTipos = getTiposdeAlerta(preferencias.obtenerToken() ?: "nada")
//        Log.d("arraykk", prueba)
    }

    init {
        ObtenerNotificaciones()


    }
}