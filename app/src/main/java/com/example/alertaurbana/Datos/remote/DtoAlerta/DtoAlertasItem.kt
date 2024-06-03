package com.example.alertaurbana.Datos.remote.DtoAlerta

data class DtoAlertasItem(
    val AlertaId: Int,
    val Contacto: String,
    val Descripcion: String,
    val FechaAlerta: String,
    val Fotos: List<Foto>,
    val HoraAlerta: String,
    val Telefono: String,
    val TipoDeAlerta: TipoDeAlerta,
    val Atendido : Boolean,
    val DetalleAtencion : String,
    val HoraAtencion: String,
    val FechaAtencion : String,
    val idoficina : Int,
    val user_id: Int
)