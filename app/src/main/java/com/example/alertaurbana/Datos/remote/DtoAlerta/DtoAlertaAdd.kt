package com.example.alertaurbana.Datos.remote.DtoAlerta

data class DtoAlertaAdd(
    val contacto: String,
    val creado_por: Int,
    val detalle: String,
    val fecha_alerta: String,
    val hora_alerta: String,
    val otros: String,
    val telefono: String,
    val tipodealerta: Int,
    val usuario: Int
)