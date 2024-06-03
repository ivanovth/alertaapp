package com.example.alertaurbana.Navigation

//import com.fggc.mascotacrud.core.Constants.Companion.ATENCION_SCREEN
//import com.fggc.mascotacrud.core.Constants.Companion.MASCOTAS_SCREEN
//import com.fggc.mascotacrud.core.Constants.Companion.UPDATE_ATENCION_SCREEN
//import com.fggc.mascotacrud.core.Constants.Companion.UPDATE_MASCOTAS_SCREEN
//import com.fggc.mascotacrud.core.Constants.Companion.UPDATE_USUARIOS_SCREEN
//import com.fggc.mascotacrud.core.Constants.Companion.USUARIOS_SCREEN

sealed class Screen(val route: String){
    object ROOT: Screen("root_graph")
    object  AUTHENTICATION:Screen("auth_graph")
    object InicioScreen: Screen("inicio_screen")
    object AGREGARALERTAS: Screen("agregar_alerta")
    object VERNOTIFICACIONES: Screen("ver_notifiaciones")
    object CAMARA: Screen("camara_alerta")
    object VERMAPAZONAS: Screen("ver_mapa_zonas")
    object VERMAPASELECCIONCOORDENADA: Screen("ver_mapa_seleccion_coordenada")
    object TESTSCREEN: Screen("testscreen")
    object RESTABLECERCONTRASENA: Screen("restablecer_contrasena")
    object  DETAILS : Screen("details_graph")

    object LOTESINFOSCREEN: Screen("lotes_info_screen")
    object INICIOFICHASLOTES: Screen("inico_ficha_lotes")
//    object UsuariosScreen: Screen(USUARIOS_SCREEN)
//    object AtencionScreen: Screen(ATENCION_SCREEN)
//    object UpdateMascotaScreen: Screen(UPDATE_MASCOTAS_SCREEN)
//    object UpdateUsuarioScreen: Screen(UPDATE_USUARIOS_SCREEN)
//    object UpdateAtencionScreen: Screen(UPDATE_ATENCION_SCREEN)

}
