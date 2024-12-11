package com.example.gymbuddyapp.Screens

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alparslanguney.example.nfc.R
import com.alparslanguney.example.nfc.datasource.services.AuthService
import com.alparslanguney.example.nfc.datasource.services.EntradasUsuario
import com.alparslanguney.example.nfc.datasource.services.ExcerciseService
import com.alparslanguney.example.nfc.datasource.services.GraphService
import com.alparslanguney.example.nfc.domain.use_cases.SharedPref
import com.alparslanguney.example.nfc.models.Entrada
import com.alparslanguney.example.nfc.models.Excercise
import com.alparslanguney.example.nfc.presentation.components.EjercicioItem
import com.alparslanguney.example.nfc.util.ChevronDown
import com.alparslanguney.example.nfc.util.CirclePlus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.InputStream
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatsScreen(innerPadding: PaddingValues){
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(Excercise(0,""))}
    val scope = rememberCoroutineScope()
    val sharedPref = SharedPref(LocalContext.current)
    var isLoading by remember {
        mutableStateOf(false)
    }
    var isLoadingGraph by remember {
        mutableStateOf(false)
    }
    var isLoadingEx by remember {
        mutableStateOf(false)
    }
    var excercises by remember {
        mutableStateOf(listOf<Excercise>())
    }
    var entradas by remember {
        mutableStateOf(listOf<Entrada>())
    }
    val bitmap = remember { mutableStateOf<Bitmap?>(null) }

    var showBottomSheet by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Estadísticas:",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(15.dp))

        Column(
            horizontalAlignment = Alignment.Start
        ) {

            LaunchedEffect(key1 = true) {
                scope.launch(Dispatchers.IO) {
                    try {
                        val trustAllCerts = arrayOf<javax.net.ssl.TrustManager>(
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

                        val exerciseService = Retrofit.Builder()
                            .baseUrl("https://157.230.187.109/")
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(ExcerciseService::class.java)
                        isLoading = true

                        val response = exerciseService.getExcercises()
                        Log.i("Response", response.toString())

                        isLoading = false

                        excercises = response

                    } catch (e: Exception) {
                        Log.e("StatsScreen", "Exception: ${e.message}")

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
            } else {
                Column {
                    Text(
                        text = "Selecciona el nombre del ejercicio",
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = selectedOption.nombre,
                            onValueChange = {},
                            placeholder = { Text("Ejercicio") },
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            shape = RoundedCornerShape(24.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = Color.Gray
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done,
                            ),
                            readOnly = true
                        )
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = ChevronDown,
                                contentDescription = "More",
                                modifier = Modifier.weight(1f)
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            excercises.forEach { excercise ->
                                DropdownMenuItem(
                                    text = { Text(excercise.nombre) },
                                    onClick = {
                                        selectedOption = excercise
                                        expanded = false
                                    }
                                )
                            }
                        }


                    }
                }

            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    disabledContentColor = MaterialTheme.colorScheme.onSurface,
                    disabledContainerColor = MaterialTheme.colorScheme.onSurface
                ),
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        try {
                            isLoadingGraph = true
                            val trustAllCerts = arrayOf<javax.net.ssl.TrustManager>(
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

                            val graphService = Retrofit.Builder()
                                .baseUrl("https://157.230.187.109/")
                                .client(okHttpClient)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                            val exerciseEntryService = graphService.create(GraphService::class.java)


                            suspend fun fetchImage(userId: Int, exerciseId: Int): Bitmap? {
                                return withContext(Dispatchers.IO) {
                                    try {
                                        val response = exerciseEntryService.getGraph(userId, exerciseId)
                                        if (response.isSuccessful) {
                                            val inputStream: InputStream =
                                                response.body()?.byteStream() ?: return@withContext null
                                            BitmapFactory.decodeStream(inputStream)
                                        } else {
                                            null
                                        }
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                        null
                                    }
                                }
                            }

                            val userId = sharedPref.getUserIdSharedPref()
                            val exerciseId = selectedOption.id_ex
                            val image = fetchImage(userId, exerciseId)
                            withContext(Dispatchers.Main) {
                                bitmap.value = image
                            }

                            isLoadingGraph = false


                        } catch (e: Exception) {
                            Log.e("StatsScreenImage", "Exception: ${e.message}")

                        }


                    }

                }
            ){
                Text(
                    text = "Ver Progreso",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }


            if (isLoadingGraph) {
                Box(
                    modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    contentAlignment = Alignment.Center

                ) {
                    CircularProgressIndicator()
                }
            } else {
                bitmap.value?.let { image ->
                    Image(
                        bitmap = image.asImageBitmap(),
                        contentDescription = "Gráfico de pesos",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .background(Color.Gray)
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))


        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "Ver histórico de ejercicios")
            IconButton(onClick = {
                scope.launch(Dispatchers.IO) {
                    try {
                        val trustAllCerts = arrayOf<javax.net.ssl.TrustManager>(
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

                        val entradasService = Retrofit.Builder()
                            .baseUrl("https://157.230.187.109/")
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(EntradasUsuario::class.java)
                        isLoadingEx = true

                        val response = entradasService.getUsersExcercisesById(id = sharedPref.getUserIdSharedPref())
                        Log.i("Response", response.toString())

                        isLoadingEx = false

                        entradas = response

                    } catch (e: Exception) {
                        Log.e("StatsScreenENtradas", "Exception: ${e.message}")

                    }


                }
                showBottomSheet = true
            }){
                Icon(imageVector = CirclePlus, contentDescription = "Ver Más")
            }
        }
    }

    if (showBottomSheet){
        ModalBottomSheet(onDismissRequest = {showBottomSheet = false}){
            LazyColumn(
                modifier = Modifier.padding(15.dp)
            ){
                items(entradas){
                    EjercicioItem(ejercicio = it)
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
            }
        }
}