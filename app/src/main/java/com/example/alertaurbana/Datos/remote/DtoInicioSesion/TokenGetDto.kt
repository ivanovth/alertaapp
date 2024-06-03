package com.example.alertaurbana.Datos.remote.DtoInicioSesion
import com.squareup.moshi.Json
data class TokenDto(
    @Json(name = "refresh")
    val refresh: String,
    @Json(name = "access")
    val access: String,
)