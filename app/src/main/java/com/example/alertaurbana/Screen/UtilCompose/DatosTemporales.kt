package com.example.alertaurbana.Screen.UtilCompose

object DatosTemporales {
   private  var _datosDireccion: String = ""
    val datosDireccion:String get()= _datosDireccion


    private val listeners = mutableListOf<(String) -> Unit>()

    fun updatedatosDireccion(newSessionId: String) {

        if (_datosDireccion != newSessionId) {
            _datosDireccion = newSessionId
            notifySessionIdChanged(newSessionId)
        }

    }
    fun addSessionIdChangeListener(listener: (String) -> Unit) {
        listeners.add(listener)
    }

    fun removeSessionIdChangeListener(listener: (String) -> Unit) {
        listeners.remove(listener)
    }

    private fun notifySessionIdChanged(newSessionId: String) {
        listeners.forEach { it(newSessionId) }
    }

}