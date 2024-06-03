package com.example.alertaurbana.Datos.remote.DtoInicioSesion

data class UsuarioRespustaDto(
    val celular: String,
    val dni: String,
    val email: String,
    val esuser_interno: Boolean,
    val fecha_nacimiento: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val telefono: String,
    val username: String
)