package com.example.alertaurbana.Screen.UtilCompose

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf

import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.alertaurbana.Screen.Alerta.AlertaViewmodel
import com.example.alertaurbana.Screen.Login.MyTopAppBar
import com.example.alertaurbana.Screen.Login.MyTopAppBarPop
import com.example.alertaurbana.Screen.Textos.ConstantsAlerta


import kotlinx.coroutines.runBlocking
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CamaraCelular(
    navController: NavController,

    opcion: Int?,

    viewModel: AlertaViewmodel = hiltViewModel()

    ) {


    Scaffold(

        topBar = {
            MyTopAppBarPop(" SUBIR FOTO DE LA ALERTA"){navController.popBackStack()}

        },
        content = { padding ->
            var capturedImageUri by rememberSaveable {
                mutableStateOf<Uri>(Uri.EMPTY)
            }
            var esFotoGuardada by rememberSaveable {
                mutableStateOf(false)
            }
            Spacer(modifier = Modifier.height(6.dp))


            Column(
                Modifier
                    .fillMaxSize()
                    .background(Color.Black).padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {

                val context = LocalContext.current
                val packageName = context.packageName
                val file = context.createImageFile()
                val uri = FileProvider.getUriForFile(
                    Objects.requireNonNull(context),
                    "$packageName.provider", file
                )


                val cameraLauncher =
                    rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) {
                        capturedImageUri = uri
                        Log.d("uri1", capturedImageUri.path.toString())
                    }

                val permissionLauncher = rememberLauncherForActivityResult(
                    ActivityResultContracts.RequestPermission()
                ) {
                    if (it) {
                        Toast.makeText(context, "Permiso concedidos", Toast.LENGTH_SHORT).show()
                        cameraLauncher.launch(uri)
                    } else {
                        Toast.makeText(context, "Permiso denegado", Toast.LENGTH_SHORT).show()
                    }
                }

                CardBotonIcono("Fotito", Icons.Default.CameraAlt) {
                    val permissionCheckResult =
                        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {

                        cameraLauncher.launch(uri)


                    } else {
                        // Request a permission
                        permissionLauncher.launch(Manifest.permission.CAMERA)
                    }
                    esFotoGuardada= false

                    viewModel.verGps(context)


                }
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "application/jpg"
                intent.addCategory(Intent.CATEGORY_OPENABLE)

                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
                        if (activityResult.resultCode == Activity.RESULT_OK) {
                            capturedImageUri= (activityResult.data?.data ?:"") as Uri
                           Log.d("dsdsd", activityResult.data?.data?.path.toString() )
                        }
                    }

                CardBotonIcono("Fotito", Icons.Default.FileUpload) {

                    launcher.launch(intent)
                    esFotoGuardada= true
                }



                if (capturedImageUri.path?.isNotEmpty() == true) {
                    Log.d("uri2", capturedImageUri.path.toString())
                    val painter = rememberAsyncImagePainter(capturedImageUri)
                    val inputStream: InputStream? =
                        context.contentResolver.openInputStream(capturedImageUri)
                    val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)
                    //  val imageState = rememberImageState(painter)
                    Image(
                        modifier = Modifier
                            .padding(16.dp, 8.dp)
                            .height(300.dp)
                            .fillMaxWidth(),
                        painter = painter,
                        contentDescription = null,

                        )

                    if (bitmap != null) {

                        // Reemplaza 'imageView' con tu ImageView


                        CardBotonIcono("Fotito", Icons.Default.Save) {
//                            val log =
//                                viewModel.createImageFile2(
//                                    bitmap,
//                                    context,
//                                    400codigo ?: "",
//                                    id ?: 0,
//                                    opcion ?: 0
//                                )
//                            if (log == "ok") {
//                                Toast.makeText(
//                                    context,
//                                    "Foto Agregada Exitosamente",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                navHostController.popBackStack()
//                            } else {
//                                Toast.makeText(
//                                    context,
//                                    "No se pudo agregar la imagen",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
                            val originalWidth = bitmap.width
                            val originalHeight = bitmap.height

                            // Definir las nuevas dimensiones deseadas (por ejemplo, la mitad del tamaño original)
                            val newWidth = originalWidth / 3
                            val newHeight = originalHeight / 3

                            // Redimensionar el Bitmap
                            val resizedBitmap = Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false)


                            val byteArrayOutputStream = ByteArrayOutputStream()
                            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

                            val base = Base64.encodeToString(byteArray, Base64.DEFAULT)

                            if (opcion== 1) {

                                AccesoPref().llenarImagen1(base)
                                Log.d("fsdfsd",base)
                                viewModel.imagenVer1= true
                                navController.popBackStack()
//                                if ( !esFotoGuardada){
//                                    viewModel.verGps(context)
//                                }


                                //  ordenfoto=0
                                //  miPainter = painter
                            }
                            if (opcion == 2) {
                                AccesoPref().llenarImagen2(base)
                                viewModel.imagenVer2= true
                                Log.d("fsdfsd2",base)
                                navController.popBackStack()
                                if ( !esFotoGuardada) {
                                    viewModel.verGps2(context)
                                }
                            }
                            if (opcion == 3) {
                                AccesoPref().llenarImagen3(base)
                                viewModel.imagenVer3= true
                                navController.popBackStack()
                                Log.d("fsdfsd3",base)
                                if ( !esFotoGuardada) {
                                    viewModel.verGps3(context)
                                }
                            }


                        }


                    } else {
                        Toast.makeText(context, "No se pudo agregar la imagen", Toast.LENGTH_SHORT)
                            .show()
                        // Manejar el caso en el que no se pudo decodificar el archivo como una imagen
                    }


//        Button(onClick = {
//
//
//
//              //  Log.d("dsd",bitmap.toString())
//                if (bitmap != null) {
//                    // Reemplaza 'imageView' con tu ImageView
//                    val log= createImageFile2(bitmap,context)
//
//                    Log.d("nada",log)
//                } else {
//                    // Manejar el caso en el que no se pudo decodificar el archivo como una imagen
//                }
//
//        }) {
//            Text("Guardar imagen")
//        }
                }
            }

        })
}


fun Context.createImageFile(): File {
    // Create an image file name
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"

    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )


    // Convierte el flujo de entrada en un bitmap


    return image
}

//fun createImageFile2(
//    bitmap: Bitmap,
//    context: Context,
//    codigo: String,
//    id: Int,
//    opcion: Int,
//    addFichaFoto: (fotos: Fotos) -> Unit,
//    addFichaDetalleFoto: (fotoDetalle: Ficha_detalleficha) -> Unit,
//): String {
//
//    // Create an image file name
//    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
//    val imageFileName = "JPEG_$codigo" + timeStamp + "_" + "Obs"
//    //  File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), subfolder)
//    val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//    // val storageDir = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "encuesta")
//    if (!storageDir.exists()) {
//        storageDir.mkdirs()
//    }
//
//    val image = File(storageDir, "$imageFileName.jpg")
//    Log.d("sdasdsad", image.toString())
//    try {
//        // Guardar el bitmap como imagen en la carpeta de imágenes
//        val outputStream = FileOutputStream(image)
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
//        outputStream.flush()
//        outputStream.close()
//
//        // Insertar la imagen en el MediaStore
//        val contentValues = ContentValues().apply {
//            put(MediaStore.Images.Media.DISPLAY_NAME, image.name)
//            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
//            put(MediaStore.Images.Media.DATA, image.absolutePath)
//        }
//
//        context.contentResolver.insert(
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//            } else {
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
//            },
//            contentValues
//        )
//
//        if (opcion == 1) {
//            val foto = Fotos(0, "", "", 1, "$imageFileName.jpg", id ?: 0)
//            addFichaFoto(foto)
//        }
//
//
//
//
//        return "ok"
//    } catch (e: Exception) {
//        e.printStackTrace()
//        return "nada"
//    }
//}