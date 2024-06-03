package com.example.alertaurbana.Datos.remote.DtoAlerta

data class Foto(
    val Foto: String,
    val FotoAlertaId: Int,
    val latitud: Double,
    val longitud: Double,
    val tipofoto: String
)