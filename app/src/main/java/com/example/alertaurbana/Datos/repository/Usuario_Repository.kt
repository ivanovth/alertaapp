package com.example.alertaurbana.Datos.repository


import android.util.Log
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.Crediantials
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.DatosUsuario
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.EmailRenviarDto
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.TokenDto

import com.example.alertaurbana.Datos.remote.DtoInicioSesion.UsuarioEnvioDto
import com.example.alertaurbana.Datos.remote.DtoInicioSesion.UsuarioRespustaDto
import com.example.alertaurbana.Datos.remote.TramiteInterface
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject


class Usuario_Repository @Inject constructor() {

    suspend fun getToken(
        user: String,
        password: String,
        api: TramiteInterface
    ): Result<TokenDto> {
        return try {
            val response = api.getToken(Crediantials(user, password))

            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

    suspend fun getToken2(
        user: String,
        password: String,
        api: TramiteInterface
    ): Result<TokenDto> {
        return try {
            val response = api.getToken(Crediantials(user, password))


            Result.success(response)

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            // Aquí debes adaptar el manejo de errores según la estructura de tu API

            if (!errorBody.isNullOrEmpty()) {
                try {
                    val errorJson = JSONObject(errorBody)
                    Log.d("error", errorJson.toString())


//                    val usernameError = errorJson.getJSONArray("username")
//                    val dniError = errorJson.getJSONArray("dni")
//                    val emailError = errorJson.getJSONArray("email")
//                    val contrasena = errorJson.getJSONArray("password")

                    val customErrorMessage = limpiar(errorJson.toString())

                    Result.failure(Exception(customErrorMessage))
                } catch (jsonException: JSONException) {
                    // Si no se puede convertir a JSON, manejar de manera diferente
                    Result.failure(Exception("Error con el Servidor Intente de nuevo mas tarde"))
                }
            } else {
                // En caso de que no haya un cuerpo de error específico
                Result.failure(e)
            }


        } catch (e: Exception) {
            Result.failure(e)
        }


    }








    suspend fun getUser(token: String, api: TramiteInterface): Result<DatosUsuario> {
        return try {
            val response = api.getMe("JWT $token")

            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }

    }


    suspend fun postUser(
        usuario: UsuarioEnvioDto,
        api: TramiteInterface
    ): Result<UsuarioRespustaDto> {
        return try {
            val response = api.CrearUsuario(usuario)


            Result.success(response)

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            // Aquí debes adaptar el manejo de errores según la estructura de tu API

            if (!errorBody.isNullOrEmpty()) {
                try {
                    val errorJson = JSONObject(errorBody)
                    Log.d("error", errorJson.toString())


//                    val usernameError = errorJson.getJSONArray("username")
//                    val dniError = errorJson.getJSONArray("dni")
//                    val emailError = errorJson.getJSONArray("email")
//                    val contrasena = errorJson.getJSONArray("password")

                    val customErrorMessage = limpiar(errorJson.toString())

                    Result.failure(Exception(customErrorMessage))
                } catch (jsonException: JSONException) {
                    // Si no se puede convertir a JSON, manejar de manera diferente
                    Result.failure(Exception("Error con el Servidor Intente de nuevo mas tarde"))
                }
            } else {
                // En caso de que no haya un cuerpo de error específico
                Result.failure(e)
            }


        } catch (e: Exception) {
            Result.failure(e)
        }


    }

    fun limpiar(jsonString: String): String {


        // Eliminar corchetes, comillas y llaves, y agregar saltos de línea después de cada punto
        return jsonString
            .replace("[", "")
            .replace("]", "")
            .replace("{", "")
            .replace("}", "")
            .replace("\"", "")
            .replace(".", ".\n")

    }
    suspend fun postReenviarEmail(
        reenviar: EmailRenviarDto,
        api: TramiteInterface
    ): Result<Unit> {
        return try {
            val response = api.Reenviar_Email(reenviar)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                val errorBody = response.errorBody()?.string() ?: "Error desconocido"
                Result.failure(Exception("La solicitud no fue exitosa. Código: ${response.code()}, Mensaje: $errorBody"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }

    }

//    suspend fun getToken(user: String, password:String, api: TramiteInterface): Result<TokenGetDto> {
//        return try{
//            val response = api.getToken(Crediantials(user, password))
//
//            Result.success(response)
//
//        }catch (e: Exception){
//            Result.failure(e)
//        }
//
//    }

//    suspend fun getUser(token: String,  api: LoteFichaInterface): Result<UserResponse> {
//        return try{
//            val response = api.getMe("JWT $token")
//
//            Result.success(response)
//
//        }catch (e: Exception){
//            Result.failure(e)
//        }
//
//    }


//    suspend fun getPersonalAcceso(token: String,userId:Int,  api: LoteFichaInterface): Result<List<PersonalAccesoResponseItem>> {
//        return try{
//            val userResponseList = api.getPersonalAcceso("JWT $token", userId)
//
//            Result.success(userResponseList)
//
//        }catch (e: Exception){
//            Result.failure(e)
//        }
//
//    }


}