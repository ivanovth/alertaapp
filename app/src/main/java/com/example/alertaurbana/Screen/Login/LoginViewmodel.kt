package com.example.alertaurbana.Screen.Login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.EmailRenviarDto
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.UsuarioEnvioDto
import com.example.alertaurbana.Datos.remote.TramiteInterface
import com.example.alertaurbana.Datos.repository.Usuario_Repository
import com.example.alertaurbana.MainActivity
import com.example.alertaurbana.Screen.UtilCompose.AccesoPref
import com.example.alertaurbana.Screen.UtilCompose.LocationService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    private val repo: Usuario_Repository,
) : ViewModel() {
    private val locationService: LocationService = LocationService()
    var locationString by mutableStateOf("")
    val editor = MainActivity.sharedPreferences.edit()

    var respuestSicreo by mutableStateOf("")
    var respuestaReenvioemail by mutableStateOf("")

    var respuestaToken by mutableStateOf("")
    var respuestaMeusuario by mutableStateOf("")
    var coordenadas by mutableStateOf("")
    var respuestaAfirmativa by mutableStateOf(false)

    var respuestaSiesAgregadoCorrectamente by mutableStateOf(false)

    var respuestaReenv by mutableStateOf("")


    fun agregarNuevo(usuario: UsuarioEnvioDto) = viewModelScope.launch(Dispatchers.IO) {
        respuestSicreo = iniciarsesion(usuario)

    }

    suspend fun iniciarsesion(usuario: UsuarioEnvioDto): String {
        var respuesta = ""
        respuestaSiesAgregadoCorrectamente= false
        repo.postUser(usuario, TramiteInterface.instance).onSuccess {
            respuesta = "Usuario Creado Satisfactoriamente verifique su Correo para validar "
            respuestaSiesAgregadoCorrectamente= true
        }.onFailure {
            respuesta = it.message.toString()
        }

        return respuesta
    }


    fun reenviarCorreo(correo: EmailRenviarDto) = viewModelScope.launch(Dispatchers.IO) {
        respuestaReenvioemail = reenviar_correo_activacion(correo)

    }

    fun verGps(context: Context) = viewModelScope.launch(Dispatchers.IO) {
        val result=locationService.getUserLocatioin(context)

        if(result!=null){
           coordenadas = "Latitud ${result.latitude} y longitud ${result.longitude} "
        }

    }

    suspend fun reenviar_correo_activacion(correo: EmailRenviarDto): String {
        var respuesta = ""
        repo.postReenviarEmail(correo, TramiteInterface.instance).onSuccess {
            respuesta = "Se volvio a enviar el correo de activación Verifique su bandeja."
        }.onFailure {
            respuesta = it.message.toString()
        }

        return respuesta
    }

    fun obtenerToken(user: String, pasword: String) = viewModelScope.launch(Dispatchers.IO)
    {
        var refresh = ""
        var access = ""
        var idusuario = 0
        var nombre = ""
        var apellido = ""



        repo.getToken2(user, pasword, TramiteInterface.instance).onSuccess {
            refresh = it.refresh
            access = it.access


            repo.getUser(access, TramiteInterface.instance).onSuccess {
                Log.d("salio", it.username + it.id)
                idusuario = it.id
                nombre = it.first_name
                apellido = it.last_name
                //   obtenerAccesos(token, it.id)
                val acceso = AccesoPref()
                acceso.llenarshared(access, idusuario, nombre, apellido)
                respuestaAfirmativa= true
            }
                .onFailure {
                    Log.d("usuario", it.message.toString())
                    respuestaMeusuario= it.message.toString()
                }


            //obternerUsuario(access)
        }.onFailure {
            Log.d("token", it.message.toString())
            respuestaToken= it.message.toString()
        }


//        editor.putString("tipopersonal", keysave)
//        editor.apply()
//        editor.putInt("idpersonal", idpersonal)
//        editor.apply()


    }


}