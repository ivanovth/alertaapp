package com.example.alertaurbana.Datos.remote.DtoInicioSesion

data class error(
    val dni: List<String>,
    val email: List<String>,
    val fecha_nacimiento: List<String>,
    val password: List<String>,
    val username: List<String>
)