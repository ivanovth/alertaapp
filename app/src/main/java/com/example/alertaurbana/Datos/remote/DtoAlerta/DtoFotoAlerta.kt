package com.example.alertaurbana.Datos.remote.DtoAlerta

data class DtoFotoAlerta(
    val creado_por: Int,
    val fichaid: Int,
    val imagen_base64: String,
    val latitud: String,
    val longitud: String,
    val nombre: String,
    val tipofoto: String
)