package com.example.alertaurbana.Screen.UtilCompose

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Environment
import android.util.Base64
import android.util.Log
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import java.util.concurrent.CountDownLatch

class ExtraerFoto {

    fun extraer(uri: Uri, context: Context): String {

Log.d("uri",uri.path.toString())
        try {
            var anchol = 1200
            var altol = 1000
            val datos = getImageDimensions(context, uri)
            Log.d("fep", datos.toString())
            anchol = (datos.first * 0.38).toInt()
            altol = (datos.second * 0.38).toInt()


            val requestOptions = RequestOptions()
                .centerCrop()
                .override(anchol, altol)
                .diskCacheStrategy(DiskCacheStrategy.ALL)

            val bitmap = Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(requestOptions)
                .submit()
                .get()

            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, byteArrayOutputStream)
            val byteArray: ByteArray = byteArrayOutputStream.toByteArray()

            return Base64.encodeToString(byteArray, Base64.DEFAULT)
           // Log.d("imagen", "{$file.absolutePat} +  vv ${file.name}")
        } catch (e: Exception) {
            return "noexiste"
        }


        //    val imagen = uriToBase64(uri, context)
    }

    fun getImageDimensions(context: Context, imageUri: Uri): Pair<Int, Int> {
        val latch = CountDownLatch(1)
        var dimensions: Pair<Int, Int> = Pair(0, 0)

        Glide.with(context)
            .asBitmap()
            .load(imageUri)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                    // Implementa si es necesario
                }

                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val width = resource.width
                    val height = resource.height
                    dimensions = Pair(width, height)
                    latch.countDown() // Libera el bloqueo
                }
            })

        try {
            latch.await() // Bloquea hasta que se obtengan las dimensiones
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return dimensions
    }





}