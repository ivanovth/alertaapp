package com.example.alertaurbana

import android.Manifest
import android.content.Context
import android.content.SharedPreferences

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.alertaurbana.Navigation.NavGraph
import com.example.alertaurbana.ui.theme.AlertaUrbanaTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    //   @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    companion object {
        private const val PREFERENCES_NAME = "MIPREFERENCIA" // Nombre de tus SharedPreferences
        lateinit var sharedPreferences: SharedPreferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        WindowCompat.setDecorFitsSystemWindows(window,false)
        window.statusBarColor=  Color(0xFFffab2d).toArgb()
        setContent {
            AlertaUrbanaTheme {
                Agregarpermisos()
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    LoginApplication()
//                }
                NavGraph(navController = rememberNavController())
            }
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Agregarpermisos() {
    val permisionState =
        rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    LaunchedEffect(Unit) {
        permisionState.launchPermissionRequest()
    }
    if (permisionState.status.isGranted) {
        val permisionState2 =
            rememberPermissionState(permission = Manifest.permission.ACCESS_COARSE_LOCATION)
        LaunchedEffect(Unit) {
            permisionState2.launchPermissionRequest()
        }

        val permisionState3 =
            rememberPermissionState(permission = Manifest.permission.CAMERA)
            LaunchedEffect(Unit)
            {
                permisionState3.launchPermissionRequest()
            }

    }

}




//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    var texto by remember { mutableStateOf(("")) }
//    Box(modifier= Modifier
//        .background(Color.White)
//        .fillMaxSize()) {
//        Column {
//            Text(
//                text = "Hello $name!",
//                modifier = modifier
//            )
//
//            Text(
//                text = "$texto !",
//                modifier = modifier
//            )
//            Button(onClick = { texto= "Esque no me clicleas bien" }) {
//                Text(text = "Cliqueame")
//            }
//            Icon(painter = rememberVectorPainter(image = Icons.Filled.Save), contentDescription ="Hola" ,modifier= Modifier.size(80.dp))
//        }
//
//
//
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AlertaUrbanaTheme {
//        Greeting("Android")
//    }
//}


//@Composable
//fun LoginApplication(){
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login_page", builder = {
//        composable("login_page", content = { LoginPage(navController = navController) })
//        composable("register_page", content = { RegisterPage(navController = navController) })
//        composable("reset_page", content = { ResetPage(navController = navController) })
//    })
//}