package com.example.alertaurbana.Screen.UtilCompose

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.location.LocationManagerCompat.isLocationEnabled
import android.provider.Settings
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color

class FuncionesExtras {


    fun verSigpsLocationEstaArmado(context: Context): Boolean{
        val manejadordeLocacion  = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return manejadordeLocacion.isProviderEnabled(LocationManager.GPS_PROVIDER)

    }

    @Composable
    fun LocationSettingsDialog(context:Context) {
      //val context = LocalContext.current
        val openDialog = remember { mutableStateOf(true) }
        val isGpsEnabled = remember { mutableStateOf(verSigpsLocationEstaArmado(context)) }

        if (!isGpsEnabled.value && openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    // Esta función se llama cuando el usuario rechaza el diálogo (por ejemplo, presionando atrás)
                    openDialog.value = false
                },
                title = { Text("Activar GPS") },
                text = { Text("El GPS está desactivado. ¿Deseas activarlo ahora?") },
                confirmButton = {
                    Button(
                        onClick = {
                            context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                            openDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary, contentColor = Color.White)
                    ) {
                        Text("Activar")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colorScheme.primary, contentColor = Color.White)
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }



}