package com.example.alertaurbana.Datos.repository

import com.example.alertaurbana.Datos.remote.AlertaInterface
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertaAdd
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertaGet
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertasItem
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoFotoAlerta
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoNotificaciones
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoTipoAlertaItem
import com.example.alertaurbana.Datos.remote.DtoAlerta.ExitoResponse
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.Crediantials
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.DatosUsuario
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.TokenDto
import com.example.alertaurbana.Datos.remote.TramiteInterface
import javax.inject.Inject

class TipoAlerta_Repository @Inject constructor() {

    suspend fun getAlertasMedida(
        token: String,
        api: AlertaInterface
    ): Result<List<DtoAlertasItem>> {
        return try {
            val response = api.getAlertas("JWT $token")

            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun getTiposAlerta(
        token: String,
        api: AlertaInterface
    ): Result<List<DtoTipoAlertaItem>> {
        return try {
            val response = api.getLotesJefeAsignados("JWT $token")

            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun getNotificaciones(
        token: String,
        api: AlertaInterface
    ): Result<List<DtoNotificaciones>> {
        return try {
            val response = api.getNotificacionesNuevas("JWT $token")

            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }


    suspend fun postAlerta(
        alertaAdd: DtoAlertaAdd,
        token: String,
        api: AlertaInterface
    ): Result<DtoAlertaGet> {
        return try {
            val response = api.AddAlerta("JWT $token", alertaAdd)

            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun postFoto(
        fotoAlerta: DtoFotoAlerta,
        token: String,
        api: AlertaInterface
    ): Result<ExitoResponse>{
        return try {
            val response = api.AddFotoAlerta("JWT $token", fotoAlerta)

            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

}