package com.example.alertaurbana.Datos.repository

import com.example.alertaurbana.Datos.remote.AlertaInterface
import com.example.alertaurbana.Datos.remote.ApiExtras
import com.example.alertaurbana.Datos.remote.DatosMapa.DtoDirecciones
import com.example.alertaurbana.Datos.remote.DatosMapa.DtoDireccionesAproximadasItem
import com.example.alertaurbana.Datos.remote.DatosMapa.DtoDireccionesItem
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertasItem
import javax.inject.Inject

class Mapas_Repository  @Inject constructor(){

    suspend fun getDireccionAproximada(
        latitud:Double,
        longitud:Double,
        distancia: Int,
        api: ApiExtras
    ): Result<List<DtoDireccionesItem>> {
        return try {
            val response = api.obtenerDatos(longitud,latitud,distancia)

            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}