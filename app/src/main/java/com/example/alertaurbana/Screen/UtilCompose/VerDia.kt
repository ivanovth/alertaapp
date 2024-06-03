package com.example.alertaurbana.Screen.UtilCompose

import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

class VerDia {

    fun convertirFormatoHora(formato:String):String{
        val horaOriginal = formato.take(8)

        // Definir el formato original y el formato deseado
        val formatoOriginal = DateTimeFormatter.ofPattern("HH:mm:ss")
        val formatoDeseado = DateTimeFormatter.ofPattern("hh:mm a")

        // Parsear la hora original
        val horaLocal = LocalTime.parse(horaOriginal, formatoOriginal)

        // Convertir a nuevo formato
        val horaConvertida = horaLocal.format(formatoDeseado)
        return horaConvertida
    }

    fun convertirFormatoFecha(formato:String):String{
        val fechaOriginal = formato

        // Definir el formato original y el formato deseado
        val formatoOriginal = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatoDeseado = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        // Parsear la fecha original
        val fechaLocal = LocalDate.parse(fechaOriginal, formatoOriginal)

        // Convertir a nuevo formato
        val fechaConvertida = fechaLocal.format(formatoDeseado)
        return fechaConvertida
    }

    fun obtenerDiaSemanaDesdeString(): String {
        // Definir el formato de la fecha que estamos recibiendo
        val fechaActual = LocalDate.now()
       // val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        // Convertir el string a un objeto LocalDate


        // Obtener el día de la semana y retornar el nombre completo en español
        val diaSemana = fechaActual.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("es"))

        return diaSemana
    }
    fun obtenerFechaActualEnFormato(): String {
        val fechaActual = LocalDate.now()

        // Definir el formato deseado
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

        // Formatear la fecha actual al formato "yyyy-MM-dd"
        val fechaFormateada = fechaActual.format(formatter)

        return fechaFormateada
    }
    fun obtenerHoractualEnFormato(): String {
        val horaActual = LocalTime.now()

        // Definir el formato deseado
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS")

        // Formatear la fecha actual al formato "yyyy-MM-dd"
        val horaFormateada = horaActual.format(formatter)

        return horaFormateada
    }

}

