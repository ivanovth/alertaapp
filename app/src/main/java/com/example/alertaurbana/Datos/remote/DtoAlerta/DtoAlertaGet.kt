package com.example.alertaurbana.Datos.remote.DtoAlerta

data class DtoAlertaGet(
    val fecha_alerta: String,
    val id: Int,
    val tipodealerta: Int,
    val usuario: Int
)