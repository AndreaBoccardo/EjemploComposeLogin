package cl.mobdev.ejemplocomposelogin.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    isLoading: Boolean,
    onLoginClick: () -> Unit
) {
    val logoPainter = painterResource(cl.mobdev.ejemplocomposelogin.R.drawable.barra)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            stringResource(cl.mobdev.ejemplocomposelogin.R.string.login_title),
            style = MaterialTheme.typography.h3
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(horizontal = 32.dp),
            painter = logoPainter, contentDescription = "icono")
        Text(
            stringResource(cl.mobdev.ejemplocomposelogin.R.string.login_subtitle),
            style = MaterialTheme.typography.subtitle1
        )
        if (isLoading){
            CircularProgressIndicator()
        } else {
            Button(onClick = onLoginClick) {
                Text(stringResource(cl.mobdev.ejemplocomposelogin.R.string.login_cta))
            }
        }
        LegalSuff()
    }
}

@Composable
fun LegalSuff(){
    val annotatedString = buildAnnotatedString {
        append(stringResource(cl.mobdev.ejemplocomposelogin.R.string.login_legal1))
        append(" ")

        pushStringAnnotation(tag = "URL", annotation = "app://terms")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
        ){
            append(stringResource(cl.mobdev.ejemplocomposelogin.R.string.login_legal2))
        }
        pop()

        append(" ")
        append(stringResource(cl.mobdev.ejemplocomposelogin.R.string.login_legal3))
        append(" ")

        pushStringAnnotation(tag = "URL", annotation = "app://privacy")
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
        ) {
            append(stringResource(cl.mobdev.ejemplocomposelogin.R.string.login_legal4))
        }
        pop()
    }

    Box(
        contentAlignment = Alignment.Center
    ){
        ClickableText(
            modifier = Modifier.padding(24.dp),
            text = annotatedString
        ) {offset->
            annotatedString.getStringAnnotations("URL", offset, offset)
                .firstOrNull()?.let {tag->
                    Log.d("APP", "Click en ${tag.item}")
                }
        }
    }
}

@Composable
@Preview
fun LoginPreview(){
    LoginScreen(true){
        // TODO
    }
}