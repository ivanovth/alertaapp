package com.example.alertaurbana.Datos.remote.DtoInicioSesion

data class UsuarioEnvioDto(
    val celular: String,
    val dni: String,
    val email: String,
    val esuser_interno: Boolean,
    val fecha_nacimiento: String,
    val first_name: String,
    val last_name: String,
    val password: String,
    val telefono: String,
    val username: String
)