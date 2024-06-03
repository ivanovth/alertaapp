package com.example.alertaurbana.Screen.UtilCompose

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun AlertDialogWithTwoButtons(
    onPositiveButtonClick: () -> Unit,
    titulo: String,
    contenido: String
) {
    val alertDialogState = remember { mutableStateOf(true) }

    if (alertDialogState.value) {
        AlertDialog(
            onDismissRequest = {
                // Handle dismiss if needed
            },
            title = {
                Text(text = titulo)
            },
            text = {
                Text(text = contenido)
            },
            confirmButton = {
                Button(
                    onClick = {
                        onPositiveButtonClick()
                        alertDialogState.value = false
                    }
                ) {
                    Text("Aceptar")
                }
            },
        )
    }
}

