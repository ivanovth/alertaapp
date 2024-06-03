package com.example.alertaurbana.Navigation

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.alertaurbana.Screen.Login.LoginPage
import com.example.alertaurbana.Screen.Login.RegisterPage
import com.example.alertaurbana.Screen.Login.ResendPage
import com.example.alertaurbana.Screen.Login.ResetPage


//import com.example.nestednavigationbottombardemo.screens.LoginContent
//import com.example.nestednavigationbottombardemo.screens.ScreenContent

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Screen.AUTHENTICATION.route,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            LoginPage(
//                onSignUpClick = {
//                    navController.navigate(AuthScreen.SignUp.route)
//                },
//                onForgotClick = {
//                    navController.navigate(AuthScreen.Forgot.route)
//                }
                navController
            )
        }
        composable(route = AuthScreen.SignUp.route) {
            RegisterPage(navController)
        }
        composable(route = AuthScreen.Forgot.route) {
            ResetPage(navController)
        }
        composable(route = AuthScreen.Resend.route) {
           ResendPage(navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")
    object SignUp : AuthScreen(route = "SIGN_UP")
    object Forgot : AuthScreen(route = "FORGOT")

    object Resend: AuthScreen(route = "RESEND")
}