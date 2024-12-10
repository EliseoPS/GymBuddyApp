package com.example.gymbuddyapp.Screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alparslanguney.example.nfc.R
import com.alparslanguney.example.nfc.datasource.services.AuthService
import com.alparslanguney.example.nfc.datasource.services.UserService
import com.alparslanguney.example.nfc.domain.dtos.Auth
import com.alparslanguney.example.nfc.domain.use_cases.SharedPref
import com.alparslanguney.example.nfc.util.Logout
import com.alparslanguney.example.nfc.util.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Composable
fun ExerciseSelectScreen(innerPadding: PaddingValues, navController: NavController){

    val sharedPref = SharedPref(LocalContext.current)
    val scope = rememberCoroutineScope()

    var isLoading by remember {
        mutableStateOf(false)
    }
    var nombre by remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = true) {
        scope.launch(Dispatchers.IO) {
            try {
                val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {}
                        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {}
                        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                    }
                )

                val sslContext = SSLContext.getInstance("TLS").apply {
                    init(null, trustAllCerts, SecureRandom())
                }

                val okHttpClient = OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
                    .hostnameVerifier { _, _ -> true }
                    .build()

                val userService = Retrofit.Builder()
                    .baseUrl("https://157.230.187.109/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(UserService::class.java)

                isLoading = true

                val response = userService.getNombre(sharedPref.getUserIdSharedPref())
                nombre = response.body().toString()

                isLoading = false

            }
            catch (e: Exception) {
                Log.e("ExcerciseSelectScreen", "Exception: ${e.message}")
            }
        }
    }
    if (isLoading) {
        Box(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            CircularProgressIndicator()
        }
    }
    else {
        Column {
            Box(
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    )
                    .background((Color(0xFFFF931F)))
            ){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth().padding(15.dp)
                ){
                    Text(
                        text = "Hola, " + nombre,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    IconButton(onClick = {
                        sharedPref.removeUserSharedPref()
                        navController.navigate(Screens.LoginScreen.route){
                            popUpTo(Screens.LoginScreen.route) { inclusive = true }
                        }
                    }){
                        Icon(
                            imageVector = Logout,
                            contentDescription = "salir",
                            modifier = Modifier.size(30.dp),
                            tint = Color.White
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(innerPadding)
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){

                Text(
                    text = "Seleccione una opción:",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(10.dp)
                        .clickable {
                            navController.navigate(Screens.NFCEntryScreen.route)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = painterResource(R.drawable.nfcroutine),
                        contentDescription = "rutinas automaticas",
                        modifier = Modifier.size(200.dp).clip(RoundedCornerShape(16.dp))
                    )
                    Text(text = "Rutinas Automaticas")
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .padding(10.dp)
                        .clickable {
                            navController.navigate(Screens.ManualEntryScreen.route)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Image(
                        painter = painterResource(R.drawable.manualroutine),
                        contentDescription = "Rutina manual",
                        modifier = Modifier.size(200.dp)
                            .clip(RoundedCornerShape(16.dp))
                    )
                    Text(text = "Rutinas Manuales")
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ExerciseSelectScreenPreview(){
    ExerciseSelectScreen(innerPadding = PaddingValues(0.dp), navController = rememberNavController())
}