package com.example.alertaurbana.Screen.UtilCompose

import com.example.alertaurbana.MainActivity

class AccesoPref {

    val editor = MainActivity.sharedPreferences.edit()

    fun llenarshared(token: String, id: Int, nombres: String, apellidos: String) {
        editor.putString("token", token)
        editor.apply()
        editor.putString("nombre", nombres)
        editor.apply()
        editor.putString("apellidos", apellidos)
        editor.apply()
        editor.putInt("idusuario", id)
        editor.apply()


    }
    fun llenarlatitud(latitud:String){
        editor.putString("latitud", latitud)
        editor.apply()
    }

    fun llenarlongitud(longitud:String){
        editor.putString("longitud", longitud)
        editor.apply()
    }

    fun llenarlatitud2(latitud2:String){
        editor.putString("latitud2", latitud2)
        editor.apply()
    }

    fun llenarlongitud2(longitud2:String){
        editor.putString("longitud2", longitud2)
        editor.apply()
    }
    fun llenarlatitud3(latitud3:String){
        editor.putString("latitud3", latitud3)
        editor.apply()
    }

    fun llenarlongitud3(longitud3:String){
        editor.putString("longitud3", longitud3)
        editor.apply()
    }

    fun gpspermiso(permiso: String) {
        editor.putString("permiso", permiso)
        editor.apply()

    }

    fun llenarImagen1(imagen1:String){
        editor.putString("imagen1", imagen1)
        editor.apply()
    }

    fun llenarImagen2(imagen2:String){
        editor.putString("imagen2", imagen2)
        editor.apply()
    }

    fun llenarImagen3(imagen3:String){
        editor.putString("imagen3", imagen3)
        editor.apply()
    }

    fun llenarDireccionTemporal(direccion:String){
        editor.putString("direccion", direccion)
        editor.apply()
    }
    fun obtenerDireccionTemporal(): String? {
        return MainActivity.sharedPreferences.getString("direccion", "")
    }

    fun obtenerlongitud(): String? {
        return MainActivity.sharedPreferences.getString("longitud", "")
    }


    fun obtenerlatitud(): String? {
        return MainActivity.sharedPreferences.getString("latitud", "")
    }

    fun obtenerlongitud2(): String? {
        return MainActivity.sharedPreferences.getString("longitud2", "")
    }
    fun obtenerlatitud2(): String? {
        return MainActivity.sharedPreferences.getString("latitud2", "")
    }

    fun obtenerlongitud3(): String? {
        return MainActivity.sharedPreferences.getString("longitud3", "")
    }
    fun obtenerlatitud3(): String? {
        return MainActivity.sharedPreferences.getString("latitud3", "")
    }

    fun obtenerimagen1(): String? {
        return MainActivity.sharedPreferences.getString("imagen1", "")
    }
    fun obtenerimagen2(): String? {
        return MainActivity.sharedPreferences.getString("imagen2", "")
    }
    fun obtenerimagen3(): String? {
        return MainActivity.sharedPreferences.getString("imagen3", "")
    }


    fun obtenerpermiso(): String? {
        return MainActivity.sharedPreferences.getString("permiso", "nada")
    }

    fun obtenerId(): Int {
        return MainActivity.sharedPreferences.getInt("idusuario", 0)
    }

    fun obtenerNombre(): String? {
        return MainActivity.sharedPreferences.getString("nombre", "nada")
    }

    fun obtenerApellidos(): String? {
        return MainActivity.sharedPreferences.getString("apellidos", "nada")
    }
    fun obtenerToken(): String? {
        return MainActivity.sharedPreferences.getString("token", "nada")
    }



    }


