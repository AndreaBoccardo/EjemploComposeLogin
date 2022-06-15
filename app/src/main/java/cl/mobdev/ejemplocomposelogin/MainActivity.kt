package cl.mobdev.ejemplocomposelogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import cl.mobdev.ejemplocomposelogin.ui.LoginScreen
import cl.mobdev.ejemplocomposelogin.ui.theme.EjemploComposeLoginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isLoading by rememberSaveable { mutableStateOf(false) }
            EjemploComposeLoginTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(isLoading){
                        isLoading = true
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    EjemploComposeLoginTheme {
        LoginScreen(true){
            //TODO
        }
    }
}