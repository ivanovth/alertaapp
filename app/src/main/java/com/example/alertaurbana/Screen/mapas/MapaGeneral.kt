package com.example.alertaurbana.Screen.mapas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.mapbox.maps.MapboxExperimental
//import com.mapbox.maps.extension.compose.MapboxMap
//import com.mapbox.maps.extension.compose.animation.viewport.MapViewportState


@OptIn(MapboxExperimental::class)
@Composable
fun MapboxMapView()
{

  //MapboxMap()



//    AndroidView(
//        factory = { context ->
//            MapView(context).apply {
//                getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS)
//            }
//        },
//        update = { mapView ->
//            mapView.getMapboxMap().apply {
//                flyTo(
//                    cameraOptions {
//                        center(Point.fromLngLat(-74.0060, 40.7128)) // New York City
//                        zoom(14.0)
//                    }
//                )
//            }
//        }
//    )
}