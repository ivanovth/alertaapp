package com.example.alertaurbana.Screen.Alerta

import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alertaurbana.Datos.remote.AlertaInterface
import com.example.alertaurbana.Datos.remote.ApiExtras
import com.example.alertaurbana.Datos.remote.DatosMapa.DtoDireccionesAproximadasItem
import com.example.alertaurbana.Datos.remote.DatosMapa.DtoDireccionesItem
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertaAdd
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoFotoAlerta
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoNotificaciones
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoTipoAlertaItem
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.EmailRenviarDto
import com.example.alertaurbana.Datos.repository.Mapas_Repository
import com.example.alertaurbana.Datos.repository.TipoAlerta_Repository
import com.example.alertaurbana.Datos.repository.Usuario_Repository
import com.example.alertaurbana.Screen.UtilCompose.AccesoPref
import com.example.alertaurbana.Screen.UtilCompose.DatosTemporales
import com.example.alertaurbana.Screen.UtilCompose.LocationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertaViewmodel @Inject constructor(
    private val repo: TipoAlerta_Repository,
    private val mapa_repo: Mapas_Repository
) : ViewModel() {
    private val locationService: LocationService = LocationService()
    val preferencias = AccesoPref()

    var ListarLotesList: List<DtoTipoAlertaItem> = listOf()

    var ListarDireccionesAproximadas: List<DtoDireccionesItem> = listOf()

    var ListarNotificacionesList: List<DtoNotificaciones> = listOf()
    var estallenadoTiposdeAlerta by mutableStateOf(false)

    var respuestaObtenerNotificaciones by mutableStateOf("")

    var respuestaObtenerTipos by mutableStateOf("")
    var respuestaObtenerDireccionesAproximadas by mutableStateOf("")
   var DireccionAproximadaRespuesta by mutableStateOf("")

//    private val _direccionAproximada = MutableLiveData<String>("")
//    val DireccionAproximadaRespuesta: LiveData<String> = _direccionAproximada


    var prueba by mutableStateOf("")

    var respuestaPostAlerta by mutableStateOf("")

    var latitud by mutableStateOf("")
    var longitud by mutableStateOf("")

    var orden by mutableStateOf(0)


    var imagen1 by mutableStateOf("")
    var imagen2 by mutableStateOf("")
    var imagen3 by mutableStateOf("")


    var imagenVer1 by mutableStateOf(false)
    var imagenVer2 by mutableStateOf(false)
    var imagenVer3 by mutableStateOf(false)

    var latitud_seleccionada by mutableStateOf("")
    var longitud_seleccionada by mutableStateOf("")

    fun setDireccionAproximada(direccion: String) {
        DireccionAproximadaRespuesta = direccion
    }
    fun Asignar_CoordenadaSeleccionada(latitud: Double,longitud: Double){
        latitud_seleccionada= latitud.toString()
        longitud_seleccionada= longitud.toString()
        Log.d("Coordenadas ", "latitud: $latitud  Longitud : $longitud")

    }

     fun AsignarCoordenadas(){
        Log.d("Asignar","Asignar coordenadas por aca ")
        if (latitud_seleccionada!="")
        {

            AccesoPref().llenarlatitud(latitud_seleccionada)
            AccesoPref().llenarlongitud(longitud_seleccionada)
        //    ObtenerDirecciones(latitud_seleccionada.toDouble(),longitud_seleccionada.toDouble(),20)
        }

    }


     //@OptIn(DelicateCoroutinesApi::class)
     fun ObtenerDirecciones( )= viewModelScope.launch(Dispatchers.IO) {
        Log.d("array", "Hptener coordenadas lanzado")
//        repo.getTiposAlerta(preferencias.obtenerToken() ?: "nada", AlertaInterface.instance)
//            .onSuccess {
//                ListarLotesList = it
//                Log.d("array", it[0].nombre)
//
//                prueba = it[0].toString()
//                respuestaObtenerTipos = "ok"
//            }.onFailure {
//                respuestaObtenerTipos = it.message.toString()
//                Log.d("error", "feoo")
//            }
         if(AccesoPref().obtenerlatitud()?.trim()!=""){
             mapa_repo.getDireccionAproximada(AccesoPref().obtenerlatitud()?.toDouble()?:0.0, AccesoPref().obtenerlongitud()?.toDouble()?:0.0,20, ApiExtras.instance)
                 .onSuccess { datos ->
                     ListarDireccionesAproximadas= datos
                     if (ListarDireccionesAproximadas.isNotEmpty()){
                         respuestaObtenerDireccionesAproximadas="ok"

                         val tDireccionAproximadaRespuesta = if ( ListarDireccionesAproximadas.size== 1){
                             ( "Dist: ${ListarDireccionesAproximadas[0].nom_dist}, Nombre Via ${ListarDireccionesAproximadas[0].nom_via_c} " )
                         } else{

                             val  calles = ListarDireccionesAproximadas.joinToString(separator = ", ") { it.nom_via_c }
                             ( "Dist: ${ListarDireccionesAproximadas[0].nom_dist}, Vias Aprox: $calles " )
                         }
//                         DatosTemporales.updatedatosDireccion(tDireccionAproximadaRespuesta)
                        AccesoPref().llenarDireccionTemporal(tDireccionAproximadaRespuesta)
//                         Log.d("estos datos", DatosTemporales.datosDireccion)
                         respuestaObtenerDireccionesAproximadas= tDireccionAproximadaRespuesta
                     }
                     Log.d("array ", ListarDireccionesAproximadas.toString())
                     //Log.d("Lista ", DireccionAproximadaRespuesta)

                 }.onFailure {
                    // respuestaObtenerDireccionesAproximadas = it.message.toString()
                     respuestaObtenerDireccionesAproximadas="No Hay calles conocidas en un radio de 20m"
                     AccesoPref().llenarDireccionTemporal("Ninguno")
                     Log.d("error", it.message.toString())

                 }
         }



//        respuestaObtenerTipos = getTiposdeAlerta(preferencias.obtenerToken() ?: "nada")
//        Log.d("arraykk", prueba)
    }

    fun ObtenerTipos() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("array", "it[0].nombre")
        repo.getTiposAlerta(preferencias.obtenerToken() ?: "nada", AlertaInterface.instance)
            .onSuccess {
                ListarLotesList = it
                Log.d("array", it[0].nombre)

                prueba = it[0].toString()
                respuestaObtenerTipos = "ok"
            }.onFailure {
                respuestaObtenerTipos = it.message.toString()
                Log.d("error", "feoo")
            }


//        respuestaObtenerTipos = getTiposdeAlerta(preferencias.obtenerToken() ?: "nada")
//        Log.d("arraykk", prueba)
    }


    fun ObtenerNotificaciones() = viewModelScope.launch(Dispatchers.IO) {
        Log.d("array", "it[0].nombre")
        repo.getNotificaciones(preferencias.obtenerToken() ?: "nada", AlertaInterface.instance)
            .onSuccess {
                ListarNotificacionesList = it
                Log.d("array", it[0].DetalleNotificacion)

                prueba = it[0].toString()
                respuestaObtenerNotificaciones = "ok"
            }.onFailure {
                respuestaObtenerNotificaciones  = it.message.toString()
                Log.d("error", "feoo")
            }


//        respuestaObtenerTipos = getTiposdeAlerta(preferencias.obtenerToken() ?: "nada")
//        Log.d("arraykk", prueba)
    }


    fun verGps(context: Context) = viewModelScope.launch(Dispatchers.IO) {
        val result = locationService.getUserLocatioin(context)

        if (result != null) {
            preferencias.llenarlatitud(result.latitude.toString())
            preferencias.llenarlongitud(result.longitude.toString())
            Log.d("Coordenada ",preferencias.obtenerlongitud()?:"")
           // ObtenerDirecciones(result.latitude,result.longitude,20)

        }
        else{
            Log.d("Es nulo","nulo las coordenadas")
        }

    }

    fun verGps2(context: Context) = viewModelScope.launch(Dispatchers.IO) {
        val result = locationService.getUserLocatioin(context)

        if (result != null) {
            preferencias.llenarlatitud2(result.latitude.toString())
            preferencias.llenarlongitud2(result.longitude.toString())

        }

    }
    fun verGps3(context: Context) = viewModelScope.launch(Dispatchers.IO) {
        val result = locationService.getUserLocatioin(context)

        if (result != null) {
            preferencias.llenarlatitud3(result.latitude.toString())
            preferencias.llenarlongitud3(result.longitude.toString())

        }

    }

    suspend fun getTiposdeAlerta(token: String): String {
        var respuesta = ""
        repo.getTiposAlerta(token, AlertaInterface.instance)
            .onSuccess {
                ListarLotesList = it
                Log.d("array", it[0].nombre)

                prueba = it[0].toString()
                respuesta = "ok"
            }.onFailure {
                respuesta = it.message.toString()
                Log.d("error", it.message.toString())
            }
        return respuesta
    }

    fun AgregarAlerta(dtoAlertaAdd: DtoAlertaAdd) = viewModelScope.launch(Dispatchers.IO) {
        respuestaPostAlerta = postAlerta(preferencias.obtenerToken() ?: "nada", dtoAlertaAdd)
    }


    suspend fun postAlerta(token: String, dtoAlertaAdd: DtoAlertaAdd): String {
        var respuesta = ""
        repo.postAlerta(dtoAlertaAdd, token, AlertaInterface.instance)
            .onSuccess {


                val imagen1 = DtoFotoAlerta(
                    creado_por = preferencias.obtenerId(),
                    fichaid = it.id,
                    imagen_base64 = preferencias.obtenerimagen1() ?: "",
                    latitud = preferencias.obtenerlatitud() ?: "0.0",
                    longitud = preferencias.obtenerlongitud() ?: "0.0",
                    nombre = "imagen_${preferencias.obtenerId()}.jpg",
                    tipofoto = "CERCA"

                )
                repo.postFoto(imagen1, token, AlertaInterface.instance)

                if (preferencias.obtenerimagen2() != "") {
                    val imagen2 = DtoFotoAlerta(
                        creado_por = preferencias.obtenerId(),
                        fichaid = it.id,
                        imagen_base64 = preferencias.obtenerimagen2() ?: "",
                        latitud = preferencias.obtenerlatitud2() ?: "0.0",
                        longitud = preferencias.obtenerlongitud2() ?: "0.0",
                        nombre = "imagen_${preferencias.obtenerId()}.jpg",
                        tipofoto = "INTERMEDIA"

                    )
                    repo.postFoto(imagen2, token, AlertaInterface.instance).isSuccess

                }


                if (preferencias.obtenerimagen3() != "") {


                    val imagen3 = DtoFotoAlerta(
                        creado_por = preferencias.obtenerId(),
                        fichaid = it.id,
                        imagen_base64 = preferencias.obtenerimagen3() ?: "",
                        latitud = preferencias.obtenerlatitud3() ?: "0.0",
                        longitud = preferencias.obtenerlongitud3() ?: "0.0",
                        nombre = "imagen_${preferencias.obtenerId()}.jpg",
                        tipofoto = "PANORAMICA"
                    )

                    repo.postFoto(imagen3, token, AlertaInterface.instance)
                }
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
                respuesta = "ok"
            }.onFailure {
                respuesta = it.message.toString()
                Log.d("error", it.message.toString())
            }
        return respuesta
    }
//    ('PANORAMICA', 'PANORAMICA'),
//    ('INTERMEDIA', 'INTERMEDIA'),
//    ('CERCA', 'CERCA'),

    init {
        ObtenerTipos()
//        AccesoPref().llenarImagen1("")
//        AccesoPref().llenarImagen2("")
//        AccesoPref().llenarImagen3("")
//        AccesoPref().llenarlatitud("")
//        AccesoPref().llenarlongitud("")
//        AccesoPref().llenarlatitud2("")
//        AccesoPref().llenarlongitud2("")
//        AccesoPref().llenarlatitud3("")
//        AccesoPref().llenarlongitud3("")

    }
}

