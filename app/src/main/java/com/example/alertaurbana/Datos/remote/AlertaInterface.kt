package com.example.alertaurbana.Datos.remote


import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertaAdd
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertaGet
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoAlertasItem
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoFotoAlerta
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoNotificaciones
import com.example.alertaurbana.Datos.remote.DtoAlerta.DtoTipoAlertaItem
import com.example.alertaurbana.Datos.remote.DtoAlerta.ExitoResponse
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.UsuarioEnvioDto
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.UsuarioRespustaDto
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit


interface AlertaInterface {
    companion object {


        val instance = Retrofit.Builder().baseUrl("https://alerta.municusco.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS) .writeTimeout(600, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS) .build()).build().create(AlertaInterface::class.java)
    }



    @GET("api/alertasmedida")
    suspend fun getAlertas(@Header("Authorization") token: String): List<DtoAlertasItem>
    @GET("api/tipoalertas/")
    suspend fun getLotesJefeAsignados(@Header("Authorization") token: String): List<DtoTipoAlertaItem>

    @POST("/api/alertas/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
    suspend fun AddAlerta( @Header("Authorization") token: String,@Body request: DtoAlertaAdd): DtoAlertaGet


    @POST("/api/fotos/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
    suspend fun AddFotoAlerta( @Header("Authorization") token: String,@Body request: DtoFotoAlerta): ExitoResponse



    @GET("api/notificaciones/")
    suspend fun getNotificacionesNuevas(@Header("Authorization") token: String): List<DtoNotificaciones>

//
//    @GET("api/ficha/detalle_asignacion/{personalId}/{fecha}/")
//    suspend fun getLotesPersonalAsignados(@Header("Authorization") token: String, @Path("personalId") personalId:Int,@Path("fecha") fecha:String): List<AsignacionLoteItem>
//
//    @GET("api/ficha/listaasignacionjefe/{jefeId}/{fecha}/")
//    suspend fun getAsignacionesJefe(@Header("Authorization") token: String, @Path("jefeId") jefeId:Int,@Path("fecha") fecha:String): List<asignacionItem>
//
//    // endpoints para la sincronizacion de las fichas
//
//    @POST("api/ficha/agregar_lote/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
//    suspend fun AgregarLote(@Header("Authorization") token: String, @Body request: FichaLoteUpload): Lote
//
//    @PUT("api/lote/lote_detalle_update/{id}/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
//    suspend fun UpdateLote(@Header("Authorization") token: String, @Body request: Updatelote, @Path("id") id:Int ): Lote
//
//
//    @POST("api/ficha/agregar_ficha/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
//    suspend fun CrearFicha(@Header("Authorization") token: String, @Body request: NuevaFicha): FichaNuevaLeer
//
//    @POST("api/ficha/agregar_detalle/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
//    suspend fun CrearDetalleAsignacion(@Header("Authorization") token: String, @Body request: dtoDetalle_Asignacion_upload): dtoDetalle_Asignacion_Download
//
//
//    @POST("api/ficha/agregar_fichafoto/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
//    suspend fun CrearFichaFoto(@Header("Authorization") token: String, @Body request: FichaFoto): ExitoResponse
//
//
//    @POST("api/ficha/agregar_detalleficha/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
//    suspend fun CrearFichaDetalle(@Header("Authorization") token: String, @Body request: FichaDetalle): ExitoResponse
//
//
//    @PATCH("api/ficha/modificar_detalle/{id}/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
//    suspend fun CrearModificarDetalle(@Header("Authorization") token: String, @Body request: ModificarDetalledeAsignacion, @Path("id") id:Int ): ExitoResponse
//
//
//
//
//    @GET("api/ficha/obtener/{personalId}/{fecha}/")
//    suspend fun getLotesConGeojson(@Header("Authorization") token: String, @Path("personalId") personalId:Int,@Path("fecha") fecha:String): List<dtoLoteGeojsonItem>
//
//    @GET("api/ficha/obtenerjefe/{personalId}/{fecha}/")
//    suspend fun getLotesConGeojsonJefe(@Header("Authorization") token: String, @Path("personalId") personalId:Int,@Path("fecha") fecha:String): List<dtoLoteGeojsonItem>
//

}

