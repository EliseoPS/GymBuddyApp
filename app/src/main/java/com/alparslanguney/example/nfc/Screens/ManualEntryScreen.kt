package com.example.gymbuddyapp.Screens

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.alparslanguney.example.nfc.R
import com.alparslanguney.example.nfc.datasource.services.ExcerciseService
import com.alparslanguney.example.nfc.datasource.services.ExerciseEntryService
import com.alparslanguney.example.nfc.domain.use_cases.SharedPref
import com.alparslanguney.example.nfc.models.Excercise
import com.alparslanguney.example.nfc.models.ExcerciseEntry
import com.alparslanguney.example.nfc.util.ChevronDown
import com.alparslanguney.example.nfc.util.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager


@Composable
fun ManualEntryScreen(innerPadding: PaddingValues, navController: NavController,currentDate: String){
    var reps by remember {
        mutableStateOf("")
    }
    var excercises by remember {
        mutableStateOf(listOf<Excercise>())
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var isLoadingEntry by remember {
        mutableStateOf(false)
    }
    var isSuccess by remember { mutableStateOf<Boolean?>(null) }

    var peso by remember {
        mutableStateOf("")
    }
    var expanded by remember { mutableStateOf(false) }

    var selectedOption by remember { mutableStateOf(Excercise(0,""))}

    val scope = rememberCoroutineScope()

    val sharedPref = SharedPref(LocalContext.current)

    val fecha = currentDate

    var request = ExcerciseEntry(
        date = currentDate,
        exercise_id = 0,
        id_en = 0,
        reps = 0,
        user_id = sharedPref.getUserIdSharedPref(),
        weight = 0f
    )

    var pesoError by remember { mutableStateOf<String?>(null) }
    var repsError by remember { mutableStateOf<String?>(null) }
    var exerciseError by remember { mutableStateOf<String?>(null) }


    val repsFocusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var id_ex = 0


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(innerPadding)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Ejercicio Manual",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(26.dp)
        )


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
                    Log.e("NFCEnrry", "Exception: ${e.message}")

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
                id_ex = selectedOption.id_ex

            }

        } //FIN DE CONSULTA PARA VER NOMBRES DE EJERCICIOS


        Spacer(
            modifier = Modifier.height(26.dp)
        )

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Ingresa el peso",
                color = MaterialTheme.colorScheme.onSurface
            )
            OutlinedTextField(
                value = peso,
                onValueChange = {
                    if (it.all { char -> char.isDigit() || char == '.' }) {
                        if (it.length <= 6) {
                            if (it.count { char -> char == '.' } <= 1) {
                                val parts = it.split(".")
                                if (parts.size == 1 || (parts.size == 2 && parts[1].length <= 2)) {
                                    peso = it
                                }
                            }
                        }
                    }
                },
                placeholder = { Text("Peso (kg)") },
                modifier = Modifier.fillMaxWidth().focusRequester(repsFocusRequester),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        repsFocusRequester.requestFocus()

                    }
                )
            )
        } // FIN TEXT FIELD DE PESO


        Spacer(
            modifier = Modifier.height(26.dp)
        )

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Ingresa las repeticiones",
                color = MaterialTheme.colorScheme.onSurface
            )
            OutlinedTextField(
                value = reps,
                onValueChange = {
                    if (it.all { char -> char.isDigit() }) {
                        if (it.length <= 3) {
                            reps = it
                            repsError = if (it.isEmpty()) "El campo no puede estar vacío" else null
                        }
                    } else {
                        repsError = "Solo se permiten números enteros"
                    }
                },
                placeholder = { Text("Repeticiones") },
                modifier = Modifier.fillMaxWidth().focusRequester(repsFocusRequester),
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Gray
                ),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
        } // FIN TEXT FIELD DE REPS

        Spacer(
            modifier = Modifier.height(26.dp)
        )

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onSurface,
                disabledContentColor = MaterialTheme.colorScheme.onSurface,
                disabledContainerColor = MaterialTheme.colorScheme.onSurface
            ),
            onClick = {
                var isValid = true

                if (selectedOption.id_ex == 0) {
                    exerciseError = "Debe seleccionar un ejercicio"
                    isValid = false
                } else {
                    exerciseError = null
                }

                if (peso.isEmpty() || peso.toFloatOrNull() == null) {
                    pesoError = "Debe ingresar un peso válido"
                    isValid = false
                } else {
                    pesoError = null
                }

                if (reps.isEmpty() || reps.toIntOrNull() == null) {
                    repsError = "Debe ingresar un número de repeticiones válido"
                    isValid = false
                } else {
                    repsError = null
                }
                if (isValid) {
                    scope.launch(Dispatchers.IO) {
                        isSuccess = null
                        try {
                            request = ExcerciseEntry(
                                date = currentDate,
                                exercise_id = selectedOption.id_ex,
                                id_en = 0,
                                reps = reps.toInt(),
                                user_id = sharedPref.getUserIdSharedPref(),
                                weight = peso.toFloat()
                            )
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

                            val exerciseEntryService = Retrofit.Builder()
                                .baseUrl("https://157.230.187.109/")
                                .client(okHttpClient)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build()
                                .create(ExerciseEntryService::class.java)
                            isLoadingEntry = true

                            val response = exerciseEntryService.createExerciseEntry(request)
                            Log.i("Response", response.toString())
                            if (response.isSuccessful) {
                                isLoadingEntry = false
                                isSuccess = true

                            } else {
                                println("Error: ${response.code()}")
                            }


                        } catch (e: Exception) {
                            isSuccess = false


                        }

                    }
                }


            }
        ) {
            Text(
                text = "Registrar",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
        if (exerciseError != null) {
            Text(text = exerciseError!!, color = Color.Red, fontSize = 12.sp)
        }
        if (pesoError != null) {
            Text(text = pesoError!!, color = Color.Red, fontSize = 12.sp)
        }
        if (repsError != null) {
            Text(text = repsError!!, color = Color.Red, fontSize = 12.sp)
        }
        if (isSuccess==false) {
            //CircularProgress bar
            Box(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                Text(text = "Ocurrió un error, asegurate de llenar todos los campos")
            }
        } else if(isSuccess == true){
            Box(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                contentAlignment = Alignment.Center

            ) {
                Text(text = "Ejericio Registrado!")
            }
        }
    }
}




@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ManualEntryScreenPreview(){
    ManualEntryScreen(innerPadding = PaddingValues(0.dp), navController = rememberNavController(), currentDate = "10-10-2003")
}