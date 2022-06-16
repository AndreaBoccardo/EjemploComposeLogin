package cl.mobdev.ejemplocomposelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import cl.mobdev.ejemplocomposelogin.ui.LoginScreen
import cl.mobdev.ejemplocomposelogin.ui.MainViewModel
import cl.mobdev.ejemplocomposelogin.ui.theme.EjemploComposeLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel by viewModels()
            val isLoading by viewModel.isLoading().observeAsState(false)
            val hasErrors by viewModel.hasErrors().observeAsState(false)

            EjemploComposeLoginTheme {
                    if (hasErrors) {
                      LoginErrorPopup {
                          viewModel.clearErrors()
                      }
                    }
                    LoginScreen(isLoading){
                     viewModel.loginWithGoogle()
                     }
                }
            }
        }
    }

@Composable
private fun LoginErrorPopup(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss, text = {
            Text(
                text = "No fue posible completar el ingreso \uD83D\uDE48",
                style = MaterialTheme.typography.body2
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "CERRAR")
            }
        }
    )
}

