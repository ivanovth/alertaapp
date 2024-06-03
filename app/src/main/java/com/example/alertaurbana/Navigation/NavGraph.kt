package com.example.alertaurbana.Navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.alertaurbana.Screen.Alerta.Contenido.MapaScreen
import com.example.alertaurbana.Screen.Alerta.Contenido.NotificaionesScreeen
import com.example.alertaurbana.Screen.Alerta.Contenido.testScreen
import com.example.alertaurbana.Screen.Alerta.RegistrarAlerta
import com.example.alertaurbana.Screen.Login.InicioScren
import com.example.alertaurbana.Screen.UtilCompose.CamaraCelular
import com.example.alertaurbana.Screen.mapas.MapaSeleccionCoordenada


@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Screen.ROOT.route,
        startDestination = Screen.AUTHENTICATION.route
    ) {
        authNavGraph(navController = navController)
        composable(
            route = Screen.InicioScreen.route,
//            exitTransition = {
//                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
//            },
//            popEnterTransition = {
//                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
//            }

        ) {
          //  BarraBotones()

            InicioScren(navController= navController)
        }

        composable(
            route = Screen.AGREGARALERTAS.route
//            exitTransition = {
//                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
//            },
//            popEnterTransition = {
//                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
//            }
        ) {
            //  BarraBotones()

            RegistrarAlerta(navController= navController)
        }

        composable(
            route = Screen.VERNOTIFICACIONES.route
//            exitTransition = {
//                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
//            },
//            popEnterTransition = {
//                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
//            }
        ) {
            //  BarraBotones()

            NotificaionesScreeen (navController= navController)
        }

        composable(
            route = Screen.VERMAPAZONAS.route
//            exitTransition = {
//                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
//            },
//            popEnterTransition = {
//                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
//            }
        ) {
            //  BarraBotones()

            MapaScreen (navController= navController)
        }



        composable(route =  Screen.CAMARA.route + "/{opcion}",
            arguments = listOf(navArgument(name="opcion"){type= NavType.IntType})
        ) {

            CamaraCelular(navController,it.arguments?.getInt("opcion"))
        }



        composable(
            route = Screen.VERMAPASELECCIONCOORDENADA.route,
            exitTransition = {
                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
            },
            popEnterTransition = {
                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
            }
        ) {
            //  BarraBotones()

            MapaSeleccionCoordenada(navController)
        }



        composable(
            route = Screen.TESTSCREEN.route
//            exitTransition = {
//                slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(700))
//            },
//            popEnterTransition = {
//                slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(700))
//            }
        ) {
            //  BarraBotones()

            testScreen()
        }



//        composable(
//            route = Screen.InicioScreen.route
//        ) {
//            //  BarraBotones()
//
//            InicioScren()
//        }


//        composable(
//            route = LOTESINFOSCREEN.route,
//                    deepLinks = listOf(navDeepLink { uriPattern = LOTESINFOSCREEN.route})
//        ){
//            LoteInfoScreen(navController)
//        }

//        composable(
//            route = "${UpdateMascotaScreen.route}/{$MASCOTA_ID}",
//            arguments = listOf(
//                navArgument(MASCOTA_ID){
//                    type = NavType.IntType
//                }
//            )
//        ){
//            backStackEntry ->
//            val mascotaId = backStackEntry.arguments?.getInt(MASCOTA_ID) ?:0
//            UpdateMascotaScreen(
//                mascotaId = mascotaId,
//                navigateBack = {
//                    navController.popBackStack()
//                }
//            )
//        }
//
/////ruta para mostrar usuarioss
//        composable(
//            route = UsuariosScreen.route
//        ){
//            UsuariosScreen(navController ,
//                navigateToUpdateUsuarioScreen = {
//                        usuarioId ->
//                    navController.navigate(
//                        "${UpdateUsuarioScreen.route}/${usuarioId}"
//
//                    )
//                   Log.i("Id xd",usuarioId.toString() )
//                }
//            )
//        }
//
//
//////Ruta update usuarios
//        composable(
//            route = "${UpdateUsuarioScreen.route}/{$USUARIO_ID}",
//            arguments = listOf(
//                navArgument(USUARIO_ID){
//                    type = NavType.IntType
//                }
//            )
//        ){
//                backStackEntry ->
//            val usuarioId = backStackEntry.arguments?.getInt(USUARIO_ID) ?:0
//            UpdateUsuarioScreen(
//                usuarioId = usuarioId,
//                navigateBack = {
//                    navController.popBackStack()
//                }
//            )
//        }
////ruta para mostrar Atenciones
//        composable(
//            route = AtencionScreen.route
//        ){
//            AtencionScreen(navController ,
//                navigateToUpdateAtencionScreen = {
//                        atencionId ->
//                    navController.navigate(
//                        "${UpdateAtencionScreen.route}/${atencionId}"
//
//                    )
//                    Log.i("Id xd",atencionId.toString() )
//                }
//            )
//        }

    }
}