package com.example.alertaurbana.Screen.UtilCompose

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.health.connect.datatypes.ExerciseRoute.Location
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.alertaurbana.Agregarpermisos
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine

class LocationService {
    private val PERMISSION_REQUEST_CODE=122
    @OptIn(ExperimentalCoroutinesApi::class)
    //   @SuppressLint("MissingPermission")
    suspend fun getUserLocatioin(context: Context): android.location.Location? {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        val isUserLocationGreanted = true
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled: Boolean =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )
        if (!isGpsEnabled || !isUserLocationGreanted) {
            return null


        }
        return suspendCancellableCoroutine { cont ->
            val permiso = AccesoPref()
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
//                ActivityCompat.requestPermissions(
//                    context as Activity,
//                    arrayOf(
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.ACCESS_COARSE_LOCATION
//                    ),
//                    PERMISSION_REQUEST_CODE
//                )

                permiso.gpspermiso("false")
            Log.d("noo","No tienes permiso")
            }
            else {
                permiso.gpspermiso("true")
                fusedLocationProviderClient.lastLocation.apply {
                    if (isComplete) {
                        if (isSuccessful) {
                            cont.resume(result) {}
                        } else {
                            cont.resume(null) {}
                        }
                        return@suspendCancellableCoroutine
                    }
                    addOnSuccessListener {
                        cont.resume(it) {
                        }
                    }
                    addOnFailureListener {
                        cont.resume(null) {}
                    }
                    addOnCanceledListener {
                        cont.resume(null) {}
                    }
                }
            }
        }

//        return suspendCancellableCoroutine { cont ->
//            fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    val location = task.result
//                    if (location != null) {
//                        cont.resume(location) {}
//                    } else {
//                        cont.resume(null) {}
//                    }
//                } else {
//                    cont.resume(null) {}
//                }
//            }
//        }

    }


}