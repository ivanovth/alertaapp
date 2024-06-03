package com.example.alertaurbana.Datos.remote


import com.example.alertaurbana.Datos.remote.DtoInicioSesion.Crediantials
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.DatosUsuario
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.EmailRenviarDto
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.TokenDto
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.UsuarioEnvioDto
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.UsuarioRespustaDto
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit


interface TramiteInterface {
    companion object {


        val instance = Retrofit.Builder().baseUrl("https://tramite.municusco.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS) .writeTimeout(600, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS) .build()).build().create(TramiteInterface::class.java)
    }

//    @GET("lotes")
//    suspend fun getLotes(): List<FichaLoteDto>
//
    @POST("api/auth/jwt/create/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
    suspend fun getToken(@Body request: Crediantials): TokenDto

    @GET("api/auth/users/me/")
    suspend fun getMe(@Header("Authorization") token: String): DatosUsuario

    @POST("/api/auth/users/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
    suspend fun CrearUsuario( @Body request: UsuarioEnvioDto): UsuarioRespustaDto


    @POST("/api/auth/users/resend_activation/") // Reemplaza "ruta/a/tu/api" por la ruta real de la API
    suspend fun Reenviar_Email( @Body request: EmailRenviarDto): Response<Unit>

//
//    @GET("api/personal/personal_acceso_detalle/{userId}/")
//    suspend fun getPersonalAcceso(@Header("Authorization") token: String, @Path("userId") userId:Int): List<PersonalAccesoResponseItem>
//
//
//    @GET("api/ficha/detalle_jefeasignacion/{personalId}/{fecha}/")
//    suspend fun getLotesJefeAsignados(@Header("Authorization") token: String, @Path("personalId") personalId:Int,@Path("fecha") fecha:String): List<AsignacionLoteItem>
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

