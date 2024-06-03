package com.example.alertaurbana.Datos.remote.DtoInicioSesion

data class DatosUsuario(
    val email: String,
    val first_name: String,
    val id: Int,
    val is_active: Boolean,
    val is_staff: Boolean,
    val is_superuser: Boolean,
    val last_name: String,
    val username: String
)

