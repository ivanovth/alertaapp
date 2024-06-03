package com.example.alertaurbana.Datos.remote.DtoAlerta

data class DtoNotificaciones(
    val id: Int,
    val TituloNotificacion: String,
    val DetalleNotificacion: String,
    val fechaNotificacion: String,
    val leido: Boolean,
)