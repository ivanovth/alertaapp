package com.example.alertaurbana.Screen.Alerta.Contenido.ViewModels

import android.graphics.Point
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alertaurbana.Datos.remote.AlertaInterface
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertasItem
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoNotificaciones
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoTipoAlertaItem
import com.example.alertaurbana.Datos.repository.TipoAlerta_Repository
import com.example.alertaurbana.Screen.UtilCompose.AccesoPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MapaScreenViewModel @Inject constructor(
    private val repo: TipoAlerta_Repository
) : ViewModel() {


    val preferencias = AccesoPref()

    var ListarAlertasList: List<DtoAlertasItem> = listOf()

    var ListarNotificacionesList: List<DtoNotificaciones> = listOf()
    var estallenadoTipodemapa by mutableIntStateOf(1)

    var respuestaObtenerNotificaciones by mutableStateOf("")
    var respuestaObtenerAlertas by mutableStateOf("")

    private val _estadodelMapa = MutableLiveData(0)
    val estadoNumero: LiveData<Int> = _estadodelMapa


    var latitud2 by mutableDoubleStateOf(0.0)
    var longitud3 by mutableDoubleStateOf(0.0)

    lateinit var datosAlerta :DtoAlertasItem




    fun updateEstado(estado : Int) {
        _estadodelMapa.value = estado

    }

    fun updateLatitud(latitud : Double) {
        this.latitud2 = latitud

    }
    fun updateLongitud(longitud : Double) {
        longitud3=longitud

    }

    fun llenarSheet(alertadatos : DtoAlertasItem){
        datosAlerta= alertadatos
    }

    fun ObtenerAlertas() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("array", "it[0].nombre")
        repo.getAlertasMedida(preferencias.obtenerToken() ?: "nada", AlertaInterface.instance)
            .onSuccess {
                ListarAlertasList = it
                Log.d("array", it.toString())

               // prueba = it[0].toString()
                respuestaObtenerAlertas  = "ok"
            }.onFailure {
                respuestaObtenerAlertas  = it.message.toString()
                Log.d("error", "feoo")
            }


//        respuestaObtenerTipos = getTiposdeAlerta(preferencias.obtenerToken() ?: "nada")
//        Log.d("arraykk", prueba)
    }





}